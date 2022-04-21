package com.aram.smartstore.domain.shared;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {

  @Setter
  private String creatorId;
  @Setter
  private String modifierId;
  private Timestamp createdAt;
  private Timestamp modifiedAt;
}
