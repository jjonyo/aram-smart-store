package com.aram.smartstore.domain;

import com.aram.smartstore.domain.shared.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryEntity extends BaseEntity {

  private Long id;
  private Long parentId;
  private Long storeId;
  private String name;
  private Integer level;
  private String useYn;

  public void updateName(String name) {
    this.name = name;
  }
  
  public void updateUseYn(String useYn) {
    this.useYn = useYn;
  }
}
