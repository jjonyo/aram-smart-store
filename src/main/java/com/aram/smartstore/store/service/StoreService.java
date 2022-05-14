package com.aram.smartstore.store.service;

import com.aram.smartstore.category.domain.Category;
import com.aram.smartstore.category.mapper.CategoryMapper;
import com.aram.smartstore.store.domain.Store;
import com.aram.smartstore.store.domain.StoreMember;
import com.aram.smartstore.store.mapper.StoreMapper;
import com.aram.smartstore.store.mapper.StoreMemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreService {

  private final StoreMapper storeMapper;
  private final StoreMemberMapper storeMemberMapper;
  private final CategoryMapper categoryMapper;

  public static final String STORE_DEFAULT_STATE = "CLOSE";
  private static final String STORE_MEMBER_DEFAULT_TYPE = "MANAGER";
  private static final String ROOT_CATEGORY_NAME = "ROOT";

  @Transactional
  public Long saveStore(Long userId, String name, String description) {
    //스토어 추가
    Long storeId = createStore(userId, name, description);

    //스토어 멤버 추가
    addStoreMember(userId, storeId);

    //ROOT카테고리 추가
    addRootCategory(userId, storeId);

    return storeId;
  }

  private void addRootCategory(Long userId, Long storeId) {
    Category category = Category.builder()
        .storeId(storeId)
        .name(ROOT_CATEGORY_NAME)
        .level(0)
        .useYn("Y")
        .creatorId(userId.toString())
        .modifierId(userId.toString())
        .build();

    categoryMapper.insert(category);
  }

  private void addStoreMember(Long userId, Long storeId) {
    StoreMember storeMember = StoreMember.builder()
        .userId(userId)
        .storeId(storeId)
        .type(STORE_MEMBER_DEFAULT_TYPE)
        .creatorId(userId.toString())
        .modifierId(userId.toString())
        .build();

    storeMemberMapper.insert(storeMember);
    storeMemberMapper.insertHistory(storeMember);
  }

  private Long createStore(Long userId, String name, String description) {
    Store store = Store.builder()
        .name(name)
        .description(description)
        .state(STORE_DEFAULT_STATE)
        .creatorId(userId.toString())
        .modifierId(userId.toString())
        .build();

    storeMapper.insert(store);
    storeMapper.insertHistory(store);
    return store.getId();
  }
}
