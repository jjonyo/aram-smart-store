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
}
