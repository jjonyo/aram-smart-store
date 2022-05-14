package com.aram.smartstore.category.mapper;

import com.aram.smartstore.category.domain.Category;
import com.aram.smartstore.product.domain.Product;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CategoryMapper {

  void insert(Category category);

  void insertHistory(Category category);

  Optional<Category> findById(Long id);

  List<Category> findChildCategoriesById(Long id);

  void update(@Param("category") Category category, @Param("modifierId") String modifierId);

  List<Category> findChildCategoriesRecursive(Long id);
}
