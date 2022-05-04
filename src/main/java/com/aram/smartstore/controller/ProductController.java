package com.aram.smartstore.controller;

import com.aram.smartstore.controller.dto.request.SaveProductRequestDto;
import com.aram.smartstore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping("/product")
  public ResponseEntity<Long> createProduct(
      @RequestBody SaveProductRequestDto saveProductRequestDto,
      @CookieValue("login-token") Long userId
  ) {

    Long savedId = productService.saveProduct(userId, saveProductRequestDto);
    
    return new ResponseEntity<>(savedId, HttpStatus.OK);
  }
}
