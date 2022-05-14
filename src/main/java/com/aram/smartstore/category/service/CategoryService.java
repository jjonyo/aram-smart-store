package com.aram.smartstore.category.service;

import com.aram.smartstore.category.controller.dto.CategoryResponseDto;
import com.aram.smartstore.category.controller.dto.UpdateCategoryRequestDto;
import com.aram.smartstore.category.domain.Category;
import com.aram.smartstore.category.mapper.CategoryMapper;
import com.aram.smartstore.store.mapper.StoreMapper;
import com.aram.smartstore.store.mapper.StoreMemberMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryMapper categoryMapper;
  private final StoreMapper storeMapper;
  private final StoreMemberMapper storeMemberMapper;

  public static final String INVALID_CATEGORY_ID = "존재하지 않는 카테고리 아이디 입니다.";
  public static final String INVALID_STORE_ID = "존재하지 않는 스토어 아이디 입니다.";
  public static final String INVALID_STORE_MEMBER = "존재하지 않는 스토어 멤버입니다.";
  public static final String INVALID_PARENT_CATEGORY_ID = "존재하지 않는 부모 카테고리 아이디 입니다.";
  public static final String MISMATCH_STORE_ID = "다른 스토어의 카테고리에 새로운 카테고리를 생성할 수 없습니다.";
  public static final String UNABLE_TO_DELETE_ERROR = "하위 카테고리가 존재하기때문에 삭제할 수 없습니다.";

  public CategoryResponseDto findCategories(Long id) {
    Category categories = categoryMapper.findById(id)
        .orElseThrow(() -> {
          throw new IllegalArgumentException(INVALID_CATEGORY_ID);
        });

    return CategoryResponseDto.of(categories);
  }

  public List<CategoryResponseDto> findChildCategories(Long id) {
    List<Category> categoriesList = categoryMapper.findChildCategoriesById(id);

    return categoriesList.stream()
        .map(CategoryResponseDto::of)
        .collect(Collectors.toList());
  }

  public Long saveCategory(Long userId, Long parentId, Long storeId, String name) {
    //스토어 조회
    validateStore(storeId);

    //스토어 멤버 조회
    validateStoreMember(userId, storeId);

    //부모 카테고리 조회
    Category parentCategory = getParentCategory(parentId);
    if (!parentCategory.getStoreId().equals(storeId)) {
      throw new IllegalArgumentException(MISMATCH_STORE_ID);
    }

    //카테고리 생성
    Category category = createCategory(userId, parentId, storeId, name,
        parentCategory.getLevel());

    return category.getId();
  }

  private Category createCategory(Long userId, Long parentId, Long storeId, String name,
      Integer parentCategoryLevel) {
    Category category = Category.builder()
        .name(name)
        .storeId(storeId)
        .parentId(parentId)
        .level(parentCategoryLevel + 1)
        .useYn("Y")
        .creatorId(userId.toString())
        .modifierId(userId.toString())
        .build();

    categoryMapper.insert(category);
    categoryMapper.insertHistory(category);
    return category;
  }

  private Category getParentCategory(Long parentId) {
    return categoryMapper.findById(parentId)
        .orElseThrow(() -> {
          throw new IllegalArgumentException(INVALID_PARENT_CATEGORY_ID);
        });
  }

  private void validateStore(Long storeId) {
    storeMapper.findById(storeId)
        .orElseThrow(() -> {
          throw new IllegalArgumentException(INVALID_STORE_ID);
        });
  }

  private void validateStoreMember(Long userId, Long storeId) {
    storeMemberMapper.findStoreMember(storeId, userId)
        .orElseThrow(() -> {
          throw new IllegalArgumentException(INVALID_STORE_MEMBER);
        });
  }

  public Long updateCategory(Long categoryId, Long userId,
      UpdateCategoryRequestDto updateCategoryRequestDto) {
    //카테고리 조회
    Category category = getCategory(categoryId);

    //스토어멤버 조회
    Long storeId = category.getStoreId();
    validateStoreMember(userId, storeId);

    //변경값 세팅
    if (updateCategoryRequestDto.getName() != null) {
      category.updateName(updateCategoryRequestDto.getName());
    }

    categoryMapper.update(category, userId.toString());
    categoryMapper.insertHistory(category);

    return category.getId();
  }

  private Category getCategory(Long categoryId) {
    return categoryMapper.findById(categoryId)
        .orElseThrow(() -> {
          throw new IllegalArgumentException(INVALID_CATEGORY_ID);
        });
  }

  public Long deleteCategory(Long categoryId, Long userId) {
    //카테고리 조회
    Category category = getCategory(categoryId);

    //스토어멤버 조회
    Long storeId = category.getStoreId();
    validateStoreMember(userId, storeId);

    //TODO :: 리팩토링
    //하위카테고리 조회
    List<CategoryResponseDto> childCategories = findChildCategories(categoryId);
    if (!childCategories.isEmpty()) {
      throw new IllegalArgumentException(UNABLE_TO_DELETE_ERROR);
    }

    //카테고리 삭제
    category.updateUseYn("N");

    categoryMapper.update(category, userId.toString());

    return category.getId();
  }
}
