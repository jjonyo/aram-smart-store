package com.aram.smartstore.mapper;

import com.aram.smartstore.domain.ProductEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {

  void insert(ProductEntity productEntity);
}
