package com.aram.smartstore.category.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Category {

  private Long id;
  private Long parentId;
  private Long storeId;
  private String name;
  private Integer level;
  private String useYn;
  private String creatorId;
  private String modifierId;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;

  public void updateName(String name) {
    this.name = name;
  }

  public void updateUseYn(String useYn) {
    this.useYn = useYn;
  }
}
