package com.aram.smartstore.controller.dto.response;

import com.aram.smartstore.domain.CategoryEntity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CategoryResponseDto {

  private final Long id;
  private final Long parentId;
  private final Long storeId;
  private final String name;
  private final Integer level;
  private final String useYn;
  private final String creatorId;
  private final String modifierId;
  private final LocalDateTime createdAt;
  private final LocalDateTime modifiedAt;

  public static CategoryResponseDto of(CategoryEntity category) {

    return CategoryResponseDto.builder()
        .id(category.getId())
        .parentId(category.getParentId())
        .storeId(category.getStoreId())
        .name(category.getName())
        .level(category.getLevel())
        .useYn(category.getUseYn())
        .creatorId(category.getCreatorId())
        .modifierId(category.getModifierId())
        .createdAt(category.getCreatedAt())
        .modifiedAt(category.getModifiedAt())
        .build();
  }
}
