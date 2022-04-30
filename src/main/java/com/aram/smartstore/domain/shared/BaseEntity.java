package com.aram.smartstore.domain.shared;

import java.time.LocalDateTime;
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
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
}
