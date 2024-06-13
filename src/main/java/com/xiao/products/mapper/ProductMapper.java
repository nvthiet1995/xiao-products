package com.xiao.products.mapper;

import com.xiao.products.dto.ProductDetailDto;
import com.xiao.products.dto.ProductDto;
import com.xiao.products.entity.Product;
import com.xiao.products.entity.ProductDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto productToProductDto(Product product);

    Product productDtoToProduct(ProductDto productDto);

    @Mapping(target = "product", ignore = true)
    ProductDetail productDetailToProductDetailDto(ProductDetailDto productDetailDto);

    @Mapping(target = "product", ignore = true)
    ProductDetailDto productDetailDtoToProductDetail(ProductDetail productDetail);
}
