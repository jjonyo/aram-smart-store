package com.aram.smartstore.domain;

import com.aram.smartstore.domain.shared.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreEntity extends BaseEntity {

  private Long id;
  private String name;
  private String description;
  private String state;
}
