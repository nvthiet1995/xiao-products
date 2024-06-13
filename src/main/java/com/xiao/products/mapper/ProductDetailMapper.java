package com.xiao.products.mapper;

import com.xiao.products.dto.ProductDetailDto;
import com.xiao.products.entity.ProductDetail;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ProductDetailMapper {
    @Mapping(source = "product", target = "product", ignore = true)
    ProductDetail productDetailDtoToProductdetail(ProductDetailDto productDetailDto);

    @Mapping(source = "product", target = "product", ignore = true)
    Set<ProductDetail> productDetailsDtoToProductDetails(Set<ProductDetailDto> productDetailsDto);
}
