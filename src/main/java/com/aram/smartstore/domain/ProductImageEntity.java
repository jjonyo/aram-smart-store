package com.aram.smartstore.domain;

import com.aram.smartstore.domain.shared.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductImageEntity extends BaseEntity {

  private Long id;
  private Long fileId;
  private Long productId;
}
