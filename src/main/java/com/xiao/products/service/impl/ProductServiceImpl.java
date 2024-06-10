package com.xiao.products.service.impl;

import com.xiao.products.dto.ProductDto;
import com.xiao.products.entity.Product;
import com.xiao.products.exception.ResourceNotFoundException;
import com.xiao.products.mapper.ProductDetailMapper;
import com.xiao.products.mapper.ProductMapper;
import com.xiao.products.repository.ProductRepository;
import com.xiao.products.service.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductDetailMapper productDetailMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, ProductDetailMapper productDetailMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.productDetailMapper = productDetailMapper;
    }

    @Override
    public void createProduct(ProductDto productDto) {
        productRepository.save(productMapper.productDtoToProduct(productDto));
    }

    @Override
    public ProductDto findProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product", "id", String.valueOf(id))
        );
        return productMapper.productToProductDto(product);
    }

    @Override
    public Page<ProductDto> findAllProducts(int pages, int pageSize) {
        Page<Product> productPage = productRepository.findAll(PageRequest.of(pages, pageSize));
        return productPage.map(productMapper::productToProductDto);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product existingProduct = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product", "id", String.valueOf(id))
        );

        existingProduct = mapValueFieldUpdate(existingProduct, productDto);
        return productMapper.productToProductDto(productRepository.save(existingProduct));
    }

    @Override
    public void deleteProduct(Long id) {

    }

    private Product mapValueFieldUpdate(Product existingProduct, ProductDto productDto) {
        return Product.builder()
                .id(existingProduct.getId())
                .name(Objects.isNull(productDto.getName()) || productDto.getName().isEmpty() ? existingProduct.getName() : productDto.getName())
                .description(Objects.isNull(productDto.getDescription()) || productDto.getDescription().isEmpty() ? existingProduct.getDescription() : productDto.getDescription())
                .leftInStock(Objects.isNull(productDto.getLeftInStock()) ? existingProduct.getLeftInStock() : productDto.getLeftInStock())
                .productDetails(productDetailMapper.productDetailsDtoToProductDetails(productDto.getProductDetails()))
                .build();
    }
}
