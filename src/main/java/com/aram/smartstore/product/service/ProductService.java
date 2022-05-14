package com.aram.smartstore.product.service;

import com.aram.smartstore.category.domain.Category;
import com.aram.smartstore.category.mapper.CategoryMapper;
import com.aram.smartstore.product.controller.dto.SaveProductRequestDto;
import com.aram.smartstore.product.domain.Product;
import com.aram.smartstore.product.mapper.ProductMapper;
import com.aram.smartstore.store.mapper.StoreMemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final CategoryMapper categoryMapper;
  private final ProductMapper productMapper;
  private final StoreMemberMapper storeMemberMapper;

  private static final String INVALID_CATEGORY_ID = "존재하지 않는 카테고리 id 입니다.";
  private static final String INVALID_CATEGORY_LEVEL = "소분류, 세부분류 카테고리에만 상품 등록이 가능합니다.";
  public static final String INVALID_STORE_MEMBER = "존재하지 않는 스토어 멤버입니다.";


  public Long saveProduct(Long userId, SaveProductRequestDto saveProductRequestDto) {
    //카테고리 조회
    Long categoryId = saveProductRequestDto.getCategoryId();
    Category category = validateCategory(categoryId);

    //스토어 멤버 조회
    Long storeId = category.getStoreId();
    validateStoreMember(userId, storeId);

    //상품 엔티티 생성
    Product product = createProduct(userId, saveProductRequestDto, categoryId);

    return product.getId();
  }

  private Product createProduct(Long userId, SaveProductRequestDto saveProductRequestDto,
      Long categoryId) {
    Product product = Product.builder()
        .name(saveProductRequestDto.getName())
        .categoryId(categoryId)
        .description(saveProductRequestDto.getDescription())
        .price(saveProductRequestDto.getPrice())
        .quantity(saveProductRequestDto.getQuantity())
        .deleteYn("N")
        .creatorId(userId.toString())
        .modifierId(userId.toString())
        .build();

    productMapper.insert(product);
    productMapper.insertHistory(product);
    return product;
  }

  private void validateStoreMember(Long userId, Long storeId) {
    storeMemberMapper.findStoreMember(storeId, userId)
        .orElseThrow(() -> {
          throw new IllegalArgumentException(INVALID_STORE_MEMBER);
        });
  }

  private Category validateCategory(Long categoryId) {
    Category category = categoryMapper.findById(categoryId)
        .orElseThrow(() -> {
          throw new IllegalArgumentException(INVALID_CATEGORY_ID);
        });

    if (category.getLevel() < 3) {
      throw new IllegalArgumentException(INVALID_CATEGORY_LEVEL);
    }
    return category;
  }
}
