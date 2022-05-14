package com.aram.smartstore.store.mapper;

import com.aram.smartstore.store.domain.Store;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreMapper {

  void insert(Store store);

  void insertHistory(Store story);

  Optional<Store> findById(Long id);
}
