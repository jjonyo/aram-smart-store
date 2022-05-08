package com.aram.smartstore.service;

import com.aram.smartstore.controller.dto.request.SaveProductRequestDto;
import com.aram.smartstore.domain.Category;
import com.aram.smartstore.domain.Product;
import com.aram.smartstore.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final UserService userService;
  private final CategoryService categoryService;
  private final StoreMemberService storeMemberService;
  private final ProductMapper productMapper;

  public Long saveProduct(Long userId, SaveProductRequestDto saveProductRequestDto) {
    //유저 조회
    userService.findUser(userId);

    //카테고리 조회
    Category category = categoryService.findCategory(
        saveProductRequestDto.getCategoryId());

    if (category.getLevel() < 3) {
      throw new IllegalArgumentException("소분류, 세부분류 카테고리에만 상품 등록이 가능합니다.");
    }

    //스토어 멤버 조회
    Long storeId = category.getStoreId();
    storeMemberService.findStoreMember(storeId, userId);

    //상품 엔티티 생성
    Product product = Product.builder()
        .name(saveProductRequestDto.getName())
        .categoryId(saveProductRequestDto.getCategoryId())
        .description(saveProductRequestDto.getDescription())
        .price(saveProductRequestDto.getPrice())
        .quantity(saveProductRequestDto.getQuantity())
        .deleteYn("N")
        .build();
    product.setCreatorId(userId.toString());
    product.setModifierId(userId.toString());

    productMapper.insert(product);

    return product.getId();
  }
}