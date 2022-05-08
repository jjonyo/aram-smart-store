package com.aram.smartstore.service;

import com.aram.smartstore.controller.dto.request.SaveUserRequestDto;
import com.aram.smartstore.domain.User;
import com.aram.smartstore.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserMapper userMapper;

  public Long saveUser(SaveUserRequestDto saveUserRequestDto) {
    User user = saveUserRequestDto.toUser();

    user.setState("NORMAL");
    user.setCreatorId("SYSTEM");
    user.setModifierId("SYSTEM");

    userMapper.insert(user);
    return user.getId();
  }

  public Long loginUser(String username, String password) {
    User user = userMapper.findByUsername(username)
        .orElseThrow(() -> {
          throw new IllegalArgumentException("존재하지 않는 username");
        });

    if (!user.getPassword().equals(password)) {
      throw new IllegalArgumentException("일치하지 않는 password");
    }

    return user.getId();
  }

  public User findUser(Long id) {
    return userMapper.findById(id)
        .orElseThrow(() -> {
          throw new IllegalArgumentException("존재하지 않는 유저 id 입니다.");
        });
  }
}
