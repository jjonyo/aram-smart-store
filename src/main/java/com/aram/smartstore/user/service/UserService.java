package com.aram.smartstore.user.service;

import com.aram.smartstore.user.controller.dto.SaveUserRequestDto;
import com.aram.smartstore.user.domain.User;
import com.aram.smartstore.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserMapper userMapper;

  public static final String USER_DEFAULT_STATE = "NORMAL";
  public static final String USER_DEFAULT_CREATOR = "SYSTEM";
  public static final String USER_DEFAULT_MODIFIER = "SYSTEM";
  public static final String INVALID_USERNAME = "존재하지 않는 유저네임 입니다.";
  public static final String INVALID_PASSWORD = "일치하지 않는 패스워드 입니다.";

  public Long saveUser(SaveUserRequestDto saveUserRequestDto) {
    User user = saveUserRequestDto.toUser();

    user.setState(USER_DEFAULT_STATE);
    userMapper.insert(user, USER_DEFAULT_CREATOR, USER_DEFAULT_MODIFIER);
    
    return user.getId();
  }

  public Long loginUser(String username, String password) {
    User user = userMapper.findByUsername(username)
        .orElseThrow(() -> {
          throw new IllegalArgumentException(INVALID_USERNAME);
        });

    if (!user.getPassword().equals(password)) {
      throw new IllegalArgumentException(INVALID_PASSWORD);
    }

    return user.getId();
  }

  public User findUser(Long id) {
    return userMapper.findById(id)
        .orElseThrow(() -> {
          throw new IllegalArgumentException(INVALID_USERNAME);
        });
  }
}
