package com.aram.smartstore.service;

import com.aram.smartstore.controller.dto.request.SaveUserRequestDto;
import com.aram.smartstore.domain.UserEntity;
import com.aram.smartstore.domain.state.UserState;
import com.aram.smartstore.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserMapper userMapper;

  public Long saveUser(SaveUserRequestDto saveUserRequestDto) {
    UserEntity userEntity = saveUserRequestDto.toEntity();

    userEntity.setState(UserState.NORMAL);
    userEntity.setCreatorId("SYSTEM");
    userEntity.setModifierId("SYSTEM");

    userMapper.insert(userEntity);
    return userEntity.getId();
  }

  public Long loginUser(String username, String password) {
    UserEntity userEntity = userMapper.findByUsername(username);

    if (userEntity == null) {
      throw new IllegalStateException("존재하지 않는 username");
    }

    if (!userEntity.getPassword().equals(password)) {
      throw new IllegalStateException("일치하지 않는 password");
    }

    return userEntity.getId();
  }
}
