package com.aram.smartstore.product.domain;

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
public class ProductOrder {

  private Long id;
  private Long productId;
  private Long payId;
  private Integer price;
  private Integer quantity;
  private String creatorId;
  private String modifierId;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
}
