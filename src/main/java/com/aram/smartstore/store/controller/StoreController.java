package com.aram.smartstore.store.controller;

import static com.aram.smartstore.global.constants.SessionConstants.LOGIN_ID;

import com.aram.smartstore.global.constants.SessionConstants;
import com.aram.smartstore.store.controller.dto.SaveStoreRequestDto;
import com.aram.smartstore.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoreController {

  private final StoreService storeService;

  @PostMapping("/store")
  public ResponseEntity<Long> createStore(@RequestBody SaveStoreRequestDto saveStoreRequestDto,
      @RequestAttribute(LOGIN_ID) Long userId) {

    Long storeId = storeService.saveStore(userId, saveStoreRequestDto.getName(),
        saveStoreRequestDto.getDescription());

    return new ResponseEntity<>(storeId, HttpStatus.OK);
  }
}
