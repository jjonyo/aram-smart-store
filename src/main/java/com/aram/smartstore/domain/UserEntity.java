package com.aram.smartstore.domain;

import com.aram.smartstore.domain.shared.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity extends BaseEntity {

  private Long id;
  private String username;
  private String password;
  private String name;
  private String address;
  private String phoneNumber;
  @Setter
  private String state;
}
