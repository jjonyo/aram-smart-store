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
public class ProductHistoryEntity extends HistoryEntity {

  private Long sequence;
  private Long productId;
  private String name;
  private Long categoryId;
  private Integer price;
  private Integer quantity;
  private String description;
  private String deleteYn;
}
