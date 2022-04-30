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

  public CategoryResponseDto findCategories(Long id) {
    CategoryEntity categories = categoryMapper.findById(id)
        .orElseThrow(() -> {
          throw new IllegalArgumentException("존재하지 않는 카테고리 ID 입니다.");
        });

    return CategoryResponseDto.of(categories);
  }

  public List<CategoryResponseDto> findChildCategories(Long id) {
    List<CategoryEntity> categoriesList = categoryMapper.findChildCategoriesById(id);

    return categoriesList.stream()
        .map(CategoryResponseDto::of)
        .collect(Collectors.toList());
  }

}
