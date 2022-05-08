package com.aram.smartstore.service;

import com.aram.smartstore.controller.dto.request.UpdateCategoryRequestDto;
import com.aram.smartstore.controller.dto.response.CategoryResponseDto;
import com.aram.smartstore.controller.dto.response.ProductResponseDto;
import com.aram.smartstore.domain.Category;
import com.aram.smartstore.domain.Product;
import com.aram.smartstore.mapper.CategoryMapper;
import com.aram.smartstore.mapper.StoreMapper;
import java.util.ArrayList;
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
    Category categories = categoryMapper.findById(id)
        .orElseThrow(() -> {
          throw new IllegalArgumentException("존재하지 않는 카테고리 id 입니다.");
        });

    return CategoryResponseDto.of(categories);
  }

  public List<CategoryResponseDto> findChildCategories(Long id) {
    List<Category> categoriesList = categoryMapper.findChildCategoriesById(id);

    return categoriesList.stream()
        .map(CategoryResponseDto::of)
        .collect(Collectors.toList());
  }

  public Category findCategory(Long id) {
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
    Category parentCategory = findCategory(parentId);

    Category category = Category.builder()
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
    Category category = Category.builder()
        .storeId(storeId)
        .name("ROOT")
        .level(0)
        .useYn("Y")
        .build();
    category.setCreatorId(userId.toString());
    category.setModifierId(userId.toString());
    categoryMapper.insert(category);

    return category.getId();
  }

  public Long updateCategory(Long categoryId, Long userId,
      UpdateCategoryRequestDto updateCategoryRequestDto) {
    //카테고리 조회
    Category category = findCategory(categoryId);

    //스토어멤버 조회
    Long storeId = category.getStoreId();
    storeMemberService.findStoreMember(storeId, userId);

    //변경값 세팅
    if (updateCategoryRequestDto.getName() != null) {
      category.updateName(updateCategoryRequestDto.getName());
    }

    category.setModifierId(userId.toString());
    categoryMapper.update(category);

    return category.getId();
  }

  public Long deleteCategory(Long categoryId, Long userId) {
    //카테고리 조회
    Category category = findCategory(categoryId);

    //스토어멤버 조회
    Long storeId = category.getStoreId();
    storeMemberService.findStoreMember(storeId, userId);

    //하위카테고리 조회
    List<CategoryResponseDto> childCategories = findChildCategories(categoryId);
    if (!childCategories.isEmpty()) {
      throw new IllegalArgumentException("하위 카테고리가 존재하기때문에 삭제할 수 없습니다.");
    }

    //카테고리 삭제
    category.updateUseYn("N");

    category.setModifierId(userId.toString());
    categoryMapper.update(category);

    return category.getId();
  }

  public List<ProductResponseDto> findProductsByCategories(Long categoryId) {
    //모든 하위 카테고리 id 가져오기
    List<Long> categoryIdList = new ArrayList<>();
    categoryIdList.add(categoryId);
    getChildCategoryId(categoryId, categoryIdList);

    List<Product> productsByCategories = categoryMapper.findProductsByCategories(
        categoryIdList);

    return productsByCategories.stream()
        .map(ProductResponseDto::of)
        .collect(Collectors.toList());
  }

  private void getChildCategoryId(Long categoryId, List<Long> categoryIdList) {
    List<Long> childCategoryIdList = categoryMapper.findChildCategoriesById(categoryId)
        .stream()
        .map(Category::getId)
        .collect(Collectors.toList());

    categoryIdList.addAll(childCategoryIdList);

    for (Long id : childCategoryIdList) {
      getChildCategoryId(id, categoryIdList);
    }
  }
}
