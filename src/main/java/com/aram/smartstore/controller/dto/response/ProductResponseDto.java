package com.aram.smartstore.controller.dto.response;

import com.aram.smartstore.domain.ProductEntity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ProductResponseDto {

  private Long id;
  private String name;
  private Long categoryId;
  private Integer price;
  private Integer quantity;
  private String description;
  private String deleteYn;
  private String creatorId;
  private String modifierId;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;

  public static ProductResponseDto of(ProductEntity productEntity) {
    return ProductResponseDto.builder()
        .id(productEntity.getId())
        .name(productEntity.getName())
        .categoryId(productEntity.getCategoryId())
        .price(productEntity.getPrice())
        .quantity(productEntity.getQuantity())
        .description(productEntity.getDescription())
        .createdAt(productEntity.getCreatedAt())
        .modifiedAt(productEntity.getModifiedAt())
        .build();
  }
}
