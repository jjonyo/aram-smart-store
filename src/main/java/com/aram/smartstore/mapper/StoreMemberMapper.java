package com.aram.smartstore.mapper;

import com.aram.smartstore.domain.StoreMemberEntity;
import java.util.Optional;
import org.apache.ibatis.annotations.Param;

public interface StoreMemberMapper {

  void insert(StoreMemberEntity storeMemberEntity);

  Optional<StoreMemberEntity> findStoreMember(@Param("storeId") Long storeId,
      @Param("userId") Long userId);
}
