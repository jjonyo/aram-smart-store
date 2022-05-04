package com.aram.smartstore.service;

import com.aram.smartstore.domain.StoreEntity;
import com.aram.smartstore.mapper.StoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService {

  private final UserService userService;
  private final StoreMemberService storeMemberService;
  private final CategoryService categoryService;
  private final StoreMapper storeMapper;

  public StoreEntity findStore(Long id) {
    return storeMapper.findById(id)
        .orElseThrow(() -> {
          throw new IllegalArgumentException("존재하지 않는 스토어 id 입니다.");
        });
  }

  @Transactional
  public Long saveStore(Long userId, String name, String description) {
    //유저 조회
    userService.findUser(userId);

    //스토어 생성
    StoreEntity storeEntity = createStore(userId, name, description);

    //스토어 멤버 추가
    storeMemberService.saveStoreMember(userId, storeEntity.getId(), "MANAGER");

    //ROOT카테고리 추가
    categoryService.saveRootCategory(userId, storeEntity.getId());

    return storeEntity.getId();
  }

  private StoreEntity createStore(Long userId, String name, String description) {
    StoreEntity storeEntity = StoreEntity.builder()
        .name(name)
        .description(description)
        .state("CLOSE")
        .build();
    storeEntity.setCreatorId(userId.toString());
    storeEntity.setModifierId(userId.toString());
    storeMapper.insert(storeEntity);

    return storeEntity;
  }
}
