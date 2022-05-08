package com.aram.smartstore.user.mapper;

import com.aram.smartstore.user.domain.User;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

  void insert(User user);

  Optional<User> findByUsername(String username);

  Optional<User> findById(Long id);
}
