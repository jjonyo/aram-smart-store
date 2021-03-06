package com.aram.smartstore.product.mapper;

import com.aram.smartstore.category.domain.Category;
import com.aram.smartstore.product.domain.Product;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {

  void insert(Product product);

  void insertHistory(Product product);

  List<Product> findProductsByCategories(List<Category> CategoryList);
}
