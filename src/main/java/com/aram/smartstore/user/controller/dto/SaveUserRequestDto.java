package com.aram.smartstore.user.controller.dto;

import com.aram.smartstore.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaveUserRequestDto {

  private String username;
  private String password;
  private String name;
  private String address;
  private String phoneNumber;

  public User toUser() {
    return User.builder()
        .username(getUsername())
        .password(getPassword())
        .name(getName())
        .address(getAddress())
        .phoneNumber(getPhoneNumber())
        .build();
  }
}
