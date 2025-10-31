package edu.fdzc.shop.controller;


import edu.fdzc.shop.dto.ProductDto;
import edu.fdzc.shop.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping("/detail/{productId}")
    public ProductDto detail(@PathVariable("productId")String productId){
        return productService.getProductById(productId);
    }
}
