package com.aram.smartstore.store.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Store {

  private Long id;
  private String name;
  private String description;
  private String state;
  private String creatorId;
  private String modifierId;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
}
