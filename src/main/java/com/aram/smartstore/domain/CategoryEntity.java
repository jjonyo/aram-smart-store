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

  public void updateParentCategory(CategoryEntity parentCategory) {
    if (!storeId.equals(parentCategory.getStoreId())) {
      throw new IllegalArgumentException("같은 스토어의 카테고리로만 변경이 가능합니다.");
    }

    if (parentCategory.getLevel() + 1 > 4) {
      throw new IllegalArgumentException("카테고리의 레벨은 5 이상이 될 수 없습니다.");
    }

    this.parentId = parentCategory.getId();
    this.level = parentCategory.getLevel() + 1;
  }

  public void updateUseYn(String useYn) {
    this.useYn = useYn;
  }
}
