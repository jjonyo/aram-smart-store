package com.aram.smartstore.mapper;

import com.aram.smartstore.domain.Category;
import com.aram.smartstore.domain.Product;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {

  void insert(Category category);

  Optional<Category> findById(Long id);

  List<Category> findChildCategoriesById(Long id);

  void update(Category category);

  List<Product> findProductsByCategories(List<Long> categoryIdList);
}
