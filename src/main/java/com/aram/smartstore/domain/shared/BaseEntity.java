package com.aram.smartstore.domain.shared;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {

  private String creatorId;
  private String modifierId;
  private Timestamp createdAt;
  private Timestamp modifiedAt;
}
