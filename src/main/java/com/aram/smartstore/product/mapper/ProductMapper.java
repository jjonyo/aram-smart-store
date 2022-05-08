package com.aram.smartstore.product.mapper;

import com.aram.smartstore.product.domain.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {

  void insert(Product product);
}
