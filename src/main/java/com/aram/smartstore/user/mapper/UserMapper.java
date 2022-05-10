package com.aram.smartstore.user.mapper;

import com.aram.smartstore.user.domain.User;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

  void insert(@Param("user") User user, @Param("creatorId") String creatorId,
      @Param("modifierId") String modifierId);

  Optional<User> findByUsername(String username);

  Optional<User> findById(Long id);
}
