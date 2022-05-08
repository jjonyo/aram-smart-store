package com.aram.smartstore.store.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaveStoreRequestDto {

  private String name;
  private String description;
}
