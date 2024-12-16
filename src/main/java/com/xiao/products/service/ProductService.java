package com.xiao.products.service;

import com.xiao.products.dto.ProductDto;
import com.xiao.products.dto.ProductUpdateDto;
import org.springframework.data.domain.Page;

public interface ProductService {

    void createProduct(ProductDto product);

    ProductDto findProductById(Long id);

    Page<ProductDto> findAllProducts(int pages, int pageSize);

    ProductDto updateProduct(Long id, ProductUpdateDto productDto);

    void deleteProduct(Long id);
}
