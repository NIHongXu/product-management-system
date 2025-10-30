package edu.fdzc.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String id; /* 商品id */
    private String categoryId;  /* 分类Id */
    private String categoryName; /* 分类名称 */
    private String productName; /* 商品名称 */
    private String description; /* 描述 */
    private String detail; /* 详情 */
    private BigDecimal price; /* 价格 */
    private Long stockCount; /* 库存量 */
}
