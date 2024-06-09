package com.xiao.products.service.impl;

import com.xiao.products.dto.ProductDto;
import com.xiao.products.repository.ProductRepository;
import com.xiao.products.service.IProductService;
import org.springframework.data.domain.Page;

public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto createProduct(ProductDto product) {

        return null;
    }

    @Override
    public ProductDto findProductById(Long id) {
        return null;
    }

    @Override
    public Page<ProductDto> findAllProducts(int pages, int pageSize) {
        return null;
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
