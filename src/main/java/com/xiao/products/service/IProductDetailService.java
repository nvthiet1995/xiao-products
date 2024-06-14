package com.xiao.products.service;

import com.xiao.products.dto.ProductDto;
import org.springframework.data.domain.Page;

public interface IProductDetailService {

    void createProductDetail(ProductDto product);

    ProductDto findProductDetailById(Long id);

    Page<ProductDto> findAllProductDetails(int pages, int pageSize);

    ProductDto updateProductDetail(Long id, ProductDto productDto);

    void deleteProductDetail(Long id);
}
