package com.aram.smartstore.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategoryRequestDto {

  private String name;
  private Long parentId;
}
