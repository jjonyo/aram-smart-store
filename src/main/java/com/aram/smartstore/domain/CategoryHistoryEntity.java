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
public class CategoryHistoryEntity extends HistoryEntity {

  private Long sequence;
  private Long categoryId;
  private Long parentId;
  private Long storeId;
  private String name;
  private Integer level;
  private String useYn;
}