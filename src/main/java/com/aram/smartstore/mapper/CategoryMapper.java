package com.aram.smartstore.mapper;

import com.aram.smartstore.domain.CategoryEntity;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {

  Optional<CategoryEntity> findById(Long id);

  List<CategoryEntity> findChildCategoriesById(Long id);
}
