package com.aram.smartstore.mapper;

import com.aram.smartstore.domain.StoreMember;
import java.util.Optional;
import org.apache.ibatis.annotations.Param;

public interface StoreMemberMapper {

  void insert(StoreMember storeMember);

  Optional<StoreMember> findStoreMember(@Param("storeId") Long storeId,
      @Param("userId") Long userId);
}
