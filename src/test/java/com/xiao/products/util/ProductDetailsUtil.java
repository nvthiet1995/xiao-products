package com.xiao.products.util;

import com.xiao.products.dto.ProductDetailDto;
import com.xiao.products.entity.ProductDetail;

import java.util.HashSet;
import java.util.Set;

public class ProductDetailsUtil {
    public static Set<ProductDetail> buildProductDetails(){
        Set<ProductDetail> productDetails = new HashSet<>();
        productDetails.add(
            ProductDetail.builder()
            .id(1L)
            .name("Weight")
            .value("700gr")
            .build()
        );
        return productDetails;
    }

    public static Set<ProductDetailDto> buildProductDetailsDto(){
        Set<ProductDetailDto> productDetailsDto = new HashSet<>();
        productDetailsDto.add(
            ProductDetailDto.builder()
            .id(1L)
            .name("Weight")
            .value("700gr")
            .build()
        );
        return productDetailsDto;
    }
}
