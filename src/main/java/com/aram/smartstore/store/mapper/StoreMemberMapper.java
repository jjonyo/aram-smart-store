package com.aram.smartstore.store.mapper;

import com.aram.smartstore.store.domain.StoreMember;
import java.util.Optional;
import org.apache.ibatis.annotations.Param;

public interface StoreMemberMapper {

  void insert(StoreMember storeMember);

  void insertHistory(StoreMember storeMember);

  Optional<StoreMember> findStoreMember(@Param("storeId") Long storeId,
      @Param("userId") Long userId);
}
