package com.aram.smartstore.product.controller;

import static com.aram.smartstore.global.constants.SessionConstants.LOGIN_ID;

import com.aram.smartstore.product.controller.dto.ProductResponseDto;
import com.aram.smartstore.product.controller.dto.SaveProductRequestDto;
import com.aram.smartstore.product.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
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

  @GetMapping("/categories/{id}/products")
  public ResponseEntity<List<ProductResponseDto>> getProductsByCategories(
      @PathVariable("id") Long categoryId) {
    List<ProductResponseDto> products = productService.findProductsByCategories(
        categoryId);

    return new ResponseEntity<>(products, HttpStatus.OK);
  }
}
