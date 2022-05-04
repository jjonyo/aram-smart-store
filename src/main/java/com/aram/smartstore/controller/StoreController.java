package com.aram.smartstore.controller;

import com.aram.smartstore.controller.dto.request.SaveStoreRequestDto;
import com.aram.smartstore.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoreController {

  private final StoreService storeService;

  @PostMapping("/store")
  public ResponseEntity<Long> createStore(@RequestBody SaveStoreRequestDto saveStoreRequestDto,
      @CookieValue("login-token") Long userId) {
    
    Long storeId = storeService.saveStore(userId, saveStoreRequestDto.getName(),
        saveStoreRequestDto.getDescription());

    return new ResponseEntity<>(storeId, HttpStatus.OK);
  }
}
