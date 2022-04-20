package com.aram.smartstore.service;

import com.aram.smartstore.controller.dto.response.CategoryResponseDto;
import com.aram.smartstore.domain.CategoryEntity;
import com.aram.smartstore.mapper.CategoryMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryMapper categoryMapper;

  public CategoryResponseDto findById(Long id) {
    CategoryEntity category = categoryMapper.findById(id);

    if (category == null) {
      throw new IllegalStateException("존재하지 않는 카테고리 ID");
    }

    return new CategoryResponseDto(category);
  }

  public List<CategoryResponseDto> getChildCategoriesById(Long id) {
    List<CategoryEntity> categories = categoryMapper.findChildCategoriesById(id);

    return categories.stream()
        .map(CategoryResponseDto::new)
        .collect(Collectors.toList());
  }

}
