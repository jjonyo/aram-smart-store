package com.aram.smartstore.product.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaveProductRequestDto {

  private String name;
  private Long categoryId;
  private Integer price;
  private Integer quantity;
  private String description;
}
