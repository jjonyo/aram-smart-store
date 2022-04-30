package com.aram.smartstore.controller.dto.request;

import com.aram.smartstore.domain.UserEntity;
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

  public UserEntity toEntity() {
    return UserEntity.builder()
        .username(getUsername())
        .password(getPassword())
        .name(getName())
        .address(getAddress())
        .phoneNumber(getPhoneNumber())
        .build();
  }
}
