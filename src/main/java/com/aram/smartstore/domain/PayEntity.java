package com.aram.smartstore.domain;

import com.aram.smartstore.domain.shared.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PayEntity extends BaseEntity {

  private Long id;
  private Long storeId;
  private Integer amount;
  private String receiverName;
  private String receiverAddress;
  private String receiverPhoneNumber;
  private String state;
  private String cancelType;
  private String cancelReason;
}
