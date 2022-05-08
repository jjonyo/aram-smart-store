package com.aram.smartstore.mapper;

import com.aram.smartstore.domain.Store;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreMapper {

  void insert(Store store);

  Optional<Store> findById(Long id);
}
