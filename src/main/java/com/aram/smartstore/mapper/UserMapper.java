package com.aram.smartstore.mapper;

import com.aram.smartstore.domain.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface UserMapper {

  @Insert(
      "INSERT INTO user (username, password, name, address, phone_number, state, creator_id, modifier_id, created_at, modified_at)"
          + "VALUES (#{username}, #{password}, #{name}, #{address}, #{phoneNumber}, #{state}, #{creatorId}, #{modifierId}, NOW(), NOW())")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insert(UserEntity userEntity);
}
