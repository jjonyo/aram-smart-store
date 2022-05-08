package com.aram.smartstore.store.domain.service;

import com.aram.smartstore.store.domain.StoreMember;
import com.aram.smartstore.store.mapper.StoreMemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreMemberService {

  private final StoreMemberMapper storeMemberMapper;

  public Long saveStoreMember(Long userId, Long storeId, String type) {
    StoreMember storeMember = StoreMember.builder()
        .userId(userId)
        .storeId(storeId)
        .type(type)
        .build();
    storeMember.setCreatorId(userId.toString());
    storeMember.setModifierId(userId.toString());
    storeMemberMapper.insert(storeMember);

    return storeMember.getId();
  }

  public StoreMember findStoreMember(Long storeId, Long userId) {
    return storeMemberMapper.findStoreMember(storeId, userId)
        .orElseThrow(() -> {
          throw new IllegalArgumentException("존재하지 않는 스토어 멤버입니다.");
        });
  }
}
