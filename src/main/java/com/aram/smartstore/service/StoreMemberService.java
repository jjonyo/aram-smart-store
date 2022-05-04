package com.aram.smartstore.service;

import com.aram.smartstore.domain.StoreMemberEntity;
import com.aram.smartstore.mapper.StoreMemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreMemberService {

  private final StoreMemberMapper storeMemberMapper;

  public Long saveStoreMember(Long userId, Long storeId, String type) {
    StoreMemberEntity storeMemberEntity = StoreMemberEntity.builder()
        .userId(userId)
        .storeId(storeId)
        .type(type)
        .build();
    storeMemberEntity.setCreatorId(userId.toString());
    storeMemberEntity.setModifierId(userId.toString());
    storeMemberMapper.insert(storeMemberEntity);

    return storeMemberEntity.getId();
  }

  public StoreMemberEntity findStoreMember(Long storeId, Long userId) {
    return storeMemberMapper.findStoreMember(storeId, userId)
        .orElseThrow(() -> {
          throw new IllegalArgumentException("존재하지 않는 스토어 멤버입니다.");
        });
  }
}
