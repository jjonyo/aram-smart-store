package com.aram.smartstore.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
  @Setter
  private String creatorId;
  @Setter
  private String modifierId;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
}