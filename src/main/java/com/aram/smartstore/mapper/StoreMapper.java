package com.aram.smartstore.mapper;

import com.aram.smartstore.domain.StoreEntity;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreMapper {

  void insert(StoreEntity storeEntity);

  Optional<StoreEntity> findById(Long id);
}
