package com.xiao.products.mapper;

import com.xiao.products.dto.ProductDetailDto;
import com.xiao.products.entity.ProductDetail;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ProductDetailMapper {
    Set<ProductDetail> productDetailsDtoToProductDetails(Set<ProductDetailDto> productDetailsDto);
}
