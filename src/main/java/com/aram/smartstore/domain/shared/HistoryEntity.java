package com.aram.smartstore.domain.shared;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class HistoryEntity {

  private String creatorId;
  private LocalDateTime createdAt;
}
