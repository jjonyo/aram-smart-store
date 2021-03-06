package com.aram.smartstore.category.controller;

import static com.aram.smartstore.global.constants.SessionConstants.LOGIN_ID;

import com.aram.smartstore.category.controller.dto.CategoryResponseDto;
import com.aram.smartstore.category.controller.dto.SaveCategoryRequestDto;
import com.aram.smartstore.category.controller.dto.UpdateCategoryRequestDto;
import com.aram.smartstore.category.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping("/categories/{id}")
  public ResponseEntity<CategoryResponseDto> getCategoriesById(@PathVariable Long id) {
    CategoryResponseDto result = categoryService.findCategories(id);

    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @GetMapping("/categories/{id}/children")
  public ResponseEntity<List<CategoryResponseDto>> getChildCategories(@PathVariable Long id) {
    List<CategoryResponseDto> result = categoryService.findChildCategories(id);

    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @PostMapping("/category")
  public ResponseEntity<Long> createCategory(
      @RequestBody SaveCategoryRequestDto saveCategoryRequestDto,
      @RequestAttribute(LOGIN_ID) Long userId
  ) {

    Long categoryId = categoryService.saveCategory(userId, saveCategoryRequestDto.getParentId(),
        saveCategoryRequestDto.getStoreId(), saveCategoryRequestDto.getName());

    return new ResponseEntity<>(categoryId, HttpStatus.OK);
  }

  @PatchMapping("/category/{id}")
  public ResponseEntity<Long> updateCategory(@PathVariable("id") Long categoryId,
      @RequestAttribute(LOGIN_ID) Long userId,
      @RequestBody UpdateCategoryRequestDto updateCategoryRequestDto) {

    Long updatedId = categoryService.updateCategory(categoryId, userId, updateCategoryRequestDto);

    return new ResponseEntity<>(updatedId, HttpStatus.OK);
  }

  @DeleteMapping("/category/{id}")
  public ResponseEntity<Long> deleteCategory(@PathVariable("id") Long categoryId,
      @RequestAttribute(LOGIN_ID) Long userId) {

    Long deletedId = categoryService.deleteCategory(categoryId, userId);

    return new ResponseEntity<>(deletedId, HttpStatus.OK);
  }
}
