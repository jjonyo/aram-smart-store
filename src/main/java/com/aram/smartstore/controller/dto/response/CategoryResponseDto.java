package com.aram.smartstore.controller.dto.response;

import com.aram.smartstore.domain.CategoryEntity;
import java.text.SimpleDateFormat;
import lombok.Getter;

@Getter
public class CategoryResponseDto {

  private final Long id;
  private final Long parentId;
  private final Long storeId;
  private final String name;
  private final Integer level;
  private final String useYn;
  private final String creatorId;
  private final String modifierId;
  private final String createdAt;
  private final String modifiedAt;

  public CategoryResponseDto(CategoryEntity category) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    this.id = category.getId();
    this.parentId = category.getParentId();
    this.storeId = category.getStoreId();
    this.name = category.getName();
    this.level = category.getLevel();
    this.useYn = category.getUseYn();
    this.creatorId = category.getCreatorId();
    this.modifierId = category.getModifierId();
    this.createdAt = formatter.format(category.getCreatedAt());
    this.modifiedAt = formatter.format(category.getModifiedAt());
  }
}
