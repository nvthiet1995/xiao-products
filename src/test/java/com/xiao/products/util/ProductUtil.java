package com.xiao.products.util;

import com.xiao.products.dto.ProductDetailDto;
import com.xiao.products.dto.ProductDto;
import com.xiao.products.entity.Product;
import com.xiao.products.entity.ProductDetail;

import java.util.HashSet;
import java.util.Set;

public class ProductUtil {
    public static ProductDto buildProductDto(){
        return ProductDto.builder()
                .id(1L)
                .name("Iphone 14 pro max")
                .leftInStock(20)
                .description("Phone is supper vip")
                .build();
    }

    public static Product buildProduct(){
        return Product.builder()
                .id(1L)
                .name("Iphone 14 pro max")
                .leftInStock(20)
                .description("Phone is supper vip")
                .build();
    }
}
