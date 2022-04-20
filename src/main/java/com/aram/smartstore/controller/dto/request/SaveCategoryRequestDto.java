package com.aram.smartstore.controller.dto.request;

import com.aram.smartstore.domain.CategoryEntity;
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
  private Integer level;
  private String useYn;
  private String creatorId;
  private String modifierId;

  public CategoryEntity toEntity() {
    return CategoryEntity.builder()
        .parentId(getParentId())
        .storeId(getStoreId())
        .name(getName())
        .level(getLevel())
        .useYn(getUseYn())
        .build();
  }
}
