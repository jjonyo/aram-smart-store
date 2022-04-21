package com.aram.smartstore.domain;

import com.aram.smartstore.domain.shared.HistoryEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StoreHistoryEntity extends HistoryEntity {

  private Long sequence;
  private Long storeId;
  private String name;
  private String description;
  private String state;
}
