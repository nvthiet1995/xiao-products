package com.xiao.products.mapper;

import com.xiao.products.dto.ProductDto;
import com.xiao.products.dto.ProductImageDto;
import com.xiao.products.entity.Product;
import com.xiao.products.entity.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto productToProductDto(Product product);

    Product productDtoToProduct(ProductDto productDto);

    Set<ProductImageDto> productImagesToProductImagesDto(Set<ProductImage> productImages);

    @Mapping(target = "product", ignore = true)
    ProductImageDto productImageToProductImageDto(ProductImage productImage);

    @Mapping(target = "product", ignore = true)
    ProductImage productImageDtoToProductImage(ProductImageDto productImageDto);
}
