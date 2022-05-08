package com.aram.smartstore.mapper;

import com.aram.smartstore.domain.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {

  void insert(Product product);
}
