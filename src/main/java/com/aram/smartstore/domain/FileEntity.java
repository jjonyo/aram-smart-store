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
public class FileEntity extends BaseEntity {

  private Long id;
  private String name;
  private String path;
  private String saveName;
  private String extension;
  private String fullName;
  private String state;
}
