package com.aram.smartstore.mapper;

import com.aram.smartstore.domain.UserEntity;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

  void insert(UserEntity userEntity);

  Optional<UserEntity> findByUsername(String username);

  Optional<UserEntity> findById(Long id);
}
