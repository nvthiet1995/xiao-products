package com.xiao.products.mapper;

import com.xiao.products.dto.ProductDto;
import com.xiao.products.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto productToProductDto(Product product);

    Product productDtoToProduct(ProductDto productDto);

}
