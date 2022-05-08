package com.aram.smartstore.controller.dto.response;

import com.aram.smartstore.domain.Product;
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
  private String creatorId;
  private String modifierId;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;

  public static ProductResponseDto of(Product product) {
    return ProductResponseDto.builder()
        .id(product.getId())
        .name(product.getName())
        .categoryId(product.getCategoryId())
        .price(product.getPrice())
        .quantity(product.getQuantity())
        .description(product.getDescription())
        .creatorId(product.getCreatorId())
        .modifierId(product.getModifierId())
        .createdAt(product.getCreatedAt())
        .modifiedAt(product.getModifiedAt())
        .build();
  }
}
