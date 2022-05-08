package com.aram.smartstore.domain;

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
public class ApiLog {

  private Long sequence;
  private Long storeId;
  private String fullPath;
  private String domain;
  private String path;
  private String requestParam;
  private String responseParam;
  private String responseCode;
  private String creatorId;
  private LocalDateTime createdAt;
}
