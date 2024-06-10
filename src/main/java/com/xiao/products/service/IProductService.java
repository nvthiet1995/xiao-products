package com.xiao.products.service;

import com.xiao.products.dto.ProductDto;
import org.springframework.data.domain.Page;

public interface IProductService {

    void createProduct(ProductDto product);

    ProductDto findProductById(Long id);

    Page<ProductDto> findAllProducts(int pages, int pageSize);

    ProductDto updateProduct(Long id, ProductDto productDto);

    void deleteProduct(Long id);
}
