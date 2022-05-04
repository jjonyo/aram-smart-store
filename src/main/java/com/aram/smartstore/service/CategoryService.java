package com.aram.smartstore.service;

import com.aram.smartstore.controller.dto.request.UpdateCategoryRequestDto;
import com.aram.smartstore.controller.dto.response.CategoryResponseDto;
import com.aram.smartstore.domain.CategoryEntity;
import com.aram.smartstore.mapper.CategoryMapper;
import com.aram.smartstore.mapper.StoreMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryMapper categoryMapper;
  private final StoreMapper storeMapper;
  private final UserService userService;
  private final StoreMemberService storeMemberService;

  public CategoryResponseDto findCategories(Long id) {
    CategoryEntity categories = categoryMapper.findById(id)
        .orElseThrow(() -> {
          throw new IllegalArgumentException("존재하지 않는 카테고리 id 입니다.");
        });

    return CategoryResponseDto.of(categories);
  }

  public List<CategoryResponseDto> findChildCategories(Long id) {
    List<CategoryEntity> categoriesList = categoryMapper.findChildCategoriesById(id);

    return categoriesList.stream()
        .map(CategoryResponseDto::of)
        .collect(Collectors.toList());
  }

  public CategoryEntity findCategoryEntity(Long id) {
    return categoryMapper.findById(id)
        .orElseThrow(() -> {
          throw new IllegalArgumentException("존재하지 않는 카테고리 id 입니다.");
        });
  }

  public Long saveCategory(Long userId, Long parentId, Long storeId, String name) {
    //유저 조회
    userService.findUser(userId);

    //스토어 조회
    storeMapper.findById(storeId)
        .orElseThrow(() -> {
          throw new IllegalArgumentException("존재하지 않는 스토어 id 입니다.");
        });

    //스토어 멤버 조회
    storeMemberService.findStoreMember(storeId, userId);

    //카테고리 조회
    CategoryEntity parentCategory = findCategoryEntity(parentId);

    CategoryEntity category = CategoryEntity.builder()
        .name(name)
        .storeId(storeId)
        .parentId(parentId)
        .level(parentCategory.getLevel() + 1)
        .useYn("Y")
        .build();
    category.setCreatorId(userId.toString());
    category.setModifierId(userId.toString());
    categoryMapper.insert(category);

    return category.getId();
  }

  public Long saveRootCategory(Long userId, Long storeId) {
    CategoryEntity categoryEntity = CategoryEntity.builder()
        .storeId(storeId)
        .name("ROOT")
        .level(0)
        .useYn("Y")
        .build();
    categoryEntity.setCreatorId(userId.toString());
    categoryEntity.setModifierId(userId.toString());
    categoryMapper.insert(categoryEntity);

    return categoryEntity.getId();
  }

  public Long updateCategory(Long categoryId, Long userId,
      UpdateCategoryRequestDto updateCategoryRequestDto) {
    //카테고리 조회
    CategoryEntity categoryEntity = findCategoryEntity(categoryId);

    //스토어멤버 조회
    Long storeId = categoryEntity.getStoreId();
    storeMemberService.findStoreMember(storeId, userId);

    //변경값 세팅
    if (updateCategoryRequestDto.getName() != null) {
      categoryEntity.updateName(updateCategoryRequestDto.getName());
    }

    //부모 카테고리 변경
    if (updateCategoryRequestDto.getParentId() != null) {
      Long parentId = updateCategoryRequestDto.getParentId();

      CategoryEntity parentCategory = findCategoryEntity(parentId);
      categoryEntity.updateParentCategory(parentCategory);
    }

    categoryEntity.setModifierId(userId.toString());
    categoryMapper.update(categoryEntity);

    return categoryEntity.getId();
  }

  public Long deleteCategory(Long categoryId, Long userId) {
    //카테고리 조회
    CategoryEntity categoryEntity = findCategoryEntity(categoryId);

    //스토어멤버 조회
    Long storeId = categoryEntity.getStoreId();
    storeMemberService.findStoreMember(storeId, userId);

    //하위카테고리 조회
    List<CategoryResponseDto> childCategories = findChildCategories(categoryId);
    if (!childCategories.isEmpty()) {
      throw new IllegalArgumentException("하위 카테고리가 존재하기때문에 삭제할 수 없습니다.");
    }

    //카테고리 삭제
    categoryEntity.updateUseYn("N");

    categoryEntity.setModifierId(userId.toString());
    categoryMapper.update(categoryEntity);

    return categoryEntity.getId();
  }

}
