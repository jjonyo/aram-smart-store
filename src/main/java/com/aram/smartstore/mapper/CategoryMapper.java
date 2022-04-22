package com.aram.smartstore.mapper;

import com.aram.smartstore.domain.CategoryEntity;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CategoryMapper {

  @Select("SELECT * FROM category WHERE id = #{id}")
  Optional<CategoryEntity> findById(Long id);

  @Select("SELECT * FROM category WHERE parent_id = #{id} AND use_yn = 'Y'")
  List<CategoryEntity> findChildCategoriesById(Long id);
}
