package com.aram.smartstore.domain;

import com.aram.smartstore.domain.shared.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductEntity extends BaseEntity {

  private Long id;
  private String name;
  private Long categoryId;
  private Integer price;
  private Integer quantity;
  private String description;
  private String deleteYn;
}
