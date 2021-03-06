package com.aram.smartstore.pay.domain;

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
public class Pay {

  private Long id;
  private Long storeId;
  private Integer amount;
  private String receiverName;
  private String receiverAddress;
  private String receiverPhoneNumber;
  private String state;
  private String cancelType;
  private String cancelReason;
  private String creatorId;
  private String modifierId;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
}
