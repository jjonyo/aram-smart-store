package com.aram.smartstore.product.controller;

import static com.aram.smartstore.global.constants.SessionConstants.LOGIN_ID;

import com.aram.smartstore.product.controller.dto.SaveProductRequestDto;
import com.aram.smartstore.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping("/product")
  public ResponseEntity<Long> createProduct(
      @RequestBody SaveProductRequestDto saveProductRequestDto,
      @RequestAttribute(LOGIN_ID) Long userId
  ) {
    Long savedId = productService.saveProduct(userId, saveProductRequestDto);

    return new ResponseEntity<>(savedId, HttpStatus.OK);
  }
}
