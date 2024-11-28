package com.xiao.products.util;

import com.xiao.products.dto.ProductDto;
import com.xiao.products.entity.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductUtil {
    public static ProductDto buildProductDto(){
        Map<String, String> specifications = new HashMap<>();
        return ProductDto.builder()
                .name("Iphone 14 pro max")
                .leftInStock(20)
                .description("Phone is supper vip")
                .specifications(Map.of(
                        "Xuat xu", "mexico",
                        "Chat lieu", "nhua pvc"
                ))
                .build();
    }

    public static Product buildProduct(){
        return Product.builder()
                .name("Iphone 14 pro max")
                .leftInStock(20)
                .description("Phone is supper vip")
                .specifications(Map.of(
                        "Xuất xu", "Mien tay",
                        "Chat lieu", "nhua pvc"
                ))
                .build();
    }

    public static ProductDto buildProductUpdateDto(Long id){
        return ProductDto.builder()
                .id(id)
                .name("Iphone 14 pro maxxxx")
                .leftInStock(15)
                .description("Phone is supper vip pro")
                .specifications(Map.of(
                        "Xuat xu", "West Domain",
                        "Chat lieu", "iron pvc"
                ))
                .build();
    }
}
