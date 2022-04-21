package com.aram.smartstore.domain;

import com.aram.smartstore.domain.shared.HistoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayHistoryEntity extends HistoryEntity {

  private Long sequence;
  private Long payId;
  private Long storeId;
  private Integer amount;
  private String receiverName;
  private String receiverAddress;
  private String receiverPhoneNumber;
  private String state;
  private String cancelType;
  private String cancelReason;
}