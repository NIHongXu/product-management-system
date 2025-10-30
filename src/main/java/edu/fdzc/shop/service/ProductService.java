package edu.fdzc.shop.service;

import edu.fdzc.shop.dao.ProductMapper;
import edu.fdzc.shop.dto.ProductDto;
import edu.fdzc.shop.entity.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {


    private final ProductMapper productMapper;


    @Autowired
    public ProductService(ProductMapper productMapper){
        this.productMapper = productMapper;

    }

    /* 插入保存商品 */
    public boolean saveProduct(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        product.setProductName(UUID.randomUUID().toString());
        return productMapper.insert(product) > 0;
    }

    /* 搜索商品 */
    public List<ProductDto> search(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        List<Product> productList = productMapper.filter(product);
        return productList.stream()
                .map(ProductService::convertDto)
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(String productId) {
        // 1. 校验入参
        if (productId == null || productId.trim().isEmpty()) {
            throw new IllegalArgumentException("商品ID不能为空");
        }

        // 2. 查询商品
        Product product = productMapper.findById(productId);
        if (product == null) {
            // 抛异常比返回null更明确，便于调用方处理（如控制器返回404）
            throw new RuntimeException("商品不存在，ID: " + productId);
        }

        // 3. 转换为DTO
        ProductDto productDto = convertDto(product);

        // 4. 查询分类（处理所有可能的null场景）
        String categoryId = productDto.getCategoryId();
        if (categoryId == null || categoryId.trim().isEmpty()) {
            productDto.setCategoryId("未分类");
            return productDto;
        }

        return productDto;
    }

    /* 更新商品 */
    public boolean update(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        product.setUpdateTime(new Date());
        return productMapper.update(product) > 0;
    }

    /* 删除商品 */
    public boolean delete(String productId) {
        return productMapper.delete(productId) > 0;
    }

    public static ProductDto convertDto(Product product) {
        if (product != null) {
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(product, productDto);
            return productDto;

        }
        return null;
    }
}
