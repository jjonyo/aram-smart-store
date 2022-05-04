package com.aram.smartstore.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaveCategoryRequestDto {

  private Long parentId;
  private Long storeId;
  private String name;
}
