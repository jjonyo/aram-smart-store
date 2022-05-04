package com.aram.smartstore.service;

import com.aram.smartstore.domain.CategoryEntity;
import com.aram.smartstore.domain.StoreEntity;
import com.aram.smartstore.domain.StoreMemberEntity;
import com.aram.smartstore.mapper.CategoryMapper;
import com.aram.smartstore.mapper.StoreMapper;
import com.aram.smartstore.mapper.StoreMemberMapper;
import com.aram.smartstore.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService {

  private final UserService userService;
  private final StoreMapper storeMapper;
  private final StoreMemberMapper storeMemberMapper;
  private final CategoryMapper categoryMapper;

  @Transactional
  public Long saveStore(Long userId, String name, String description) {
    //유저 조회
    userService.findUser(userId);

    //스토어 생성
    StoreEntity storeEntity = createStore(userId, name, description);

    //스토어 멤버 추가
    initStoreMember(userId, storeEntity.getId());

    //ROOT카테고리 추가
    initRootCategory(userId, storeEntity.getId());

    return storeEntity.getId();
  }

  private void initRootCategory(Long userId, Long storeId) {
    CategoryEntity categoryEntity = CategoryEntity.builder()
        .storeId(storeId)
        .name("ROOT")
        .level(0)
        .useYn("Y")
        .build();
    categoryEntity.setCreatorId(userId.toString());
    categoryEntity.setModifierId(userId.toString());
    categoryMapper.insert(categoryEntity);
  }

  private void initStoreMember(Long userId, Long storeId) {
    StoreMemberEntity storeMemberEntity = StoreMemberEntity.builder()
        .userId(userId)
        .storeId(storeId)
        .type("MANAGER")
        .build();
    storeMemberEntity.setCreatorId(userId.toString());
    storeMemberEntity.setModifierId(userId.toString());
    storeMemberMapper.insert(storeMemberEntity);
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
