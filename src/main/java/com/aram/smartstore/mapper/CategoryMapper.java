package com.aram.smartstore.mapper;

import com.aram.smartstore.domain.CategoryEntity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CategoryMapper {

  @Select("SELECT * FROM category WHERE id = #{id}")
  CategoryEntity findById(Long id);

  @Select("SELECT * FROM category WHERE parent_id = #{id} AND use_yn = 'Y'")
  List<CategoryEntity> findChildCategoriesById(Long id);
}
