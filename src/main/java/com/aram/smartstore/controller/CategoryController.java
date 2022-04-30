package com.aram.smartstore.controller;

import com.aram.smartstore.controller.dto.response.CategoryResponseDto;
import com.aram.smartstore.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
