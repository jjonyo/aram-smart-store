package com.aram.smartstore.hello.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HelloMapper {
  @Select("SELECT NOW()")
  public String now();
}
