package com.aram.smartstore.domain;

import com.aram.smartstore.domain.shared.HistoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserHistoryEntity extends HistoryEntity {

  private Long sequence;
  private Long userId;
  private String userName;
  private String password;
  private String name;
  private String address;
  private String phoneNumber;
  private String state;
}
