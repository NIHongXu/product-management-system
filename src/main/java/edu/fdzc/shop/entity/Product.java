package edu.fdzc.shop.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    //分类Id
    private String categoryId;
    //产品名称
    private String productName;
    //产品描述
    private String description;
    //产品详情
    private String detail;
    //商品价格
    private BigDecimal price;
    //库存量
    private Long stockCount;


    public void setUpdateTime(Date date) {
    }
}
