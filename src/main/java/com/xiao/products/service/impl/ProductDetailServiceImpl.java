package com.xiao.products.service.impl;

import com.xiao.products.dto.ProductDto;
import com.xiao.products.service.IProductDetailService;
import org.springframework.data.domain.Page;

public class ProductDetailServiceImpl implements IProductDetailService {
    @Override
    public void createProductDetail(ProductDto product) {

    }

    @Override
    public ProductDto findProductDetailById(Long id) {
        return null;
    }

    @Override
    public Page<ProductDto> findAllProductDetails(int pages, int pageSize) {
        return null;
    }

    @Override
    public ProductDto updateProductDetail(Long id, ProductDto productDto) {
        return null;
    }

    @Override
    public void deleteProductDetail(Long id) {

    }

}
