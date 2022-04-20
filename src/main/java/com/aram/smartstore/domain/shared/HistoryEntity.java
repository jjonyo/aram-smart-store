package com.aram.smartstore.domain.shared;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class HistoryEntity {

  private String creatorId;
  private Timestamp createdAt;
}
