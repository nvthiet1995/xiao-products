package com.xiao.products.service.impl;

import com.xiao.products.dto.ProductDto;
import com.xiao.products.entity.Product;
import com.xiao.products.mapper.ProductMapper;
import com.xiao.products.repository.ProductRepository;
import com.xiao.products.util.ProductUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productRepository, productMapper);
    }

    @Test
    void testCreateProduct(){
        ProductDto productDto = ProductUtil.buildProductDto();
        productService.createProduct(productDto);

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testCreateProduct_whenProductRepositoryGotException(){
        ProductDto productDto = ProductUtil.buildProductDto();
        when(productRepository.save(any(Product.class))).thenThrow(new RuntimeException("Exception"));

        try{
            productService.createProduct(productDto);
            fail("Should throw exception");
        }catch (RuntimeException e){
            assertEquals(e.getMessage(), "Exception");
        }

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testFindProductById() {
        Long productId = 1L;
        Product product = ProductUtil.buildProduct();
        product.setId(productId);
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        ProductDto result = productService.findProductById(productId);

        assertEquals(product.getName(), result.getName());
        assertEquals(product.getId(), result.getId());
        assertEquals(product.getDescription(), result.getDescription());
        assertEquals(product.getLeftInStock(), result.getLeftInStock());
//        assertEquals(product.getSpecifications().size(), result.getSpecifications().size());
    }

    @Test
    void testFindProductById_whenNotFound() {
        Long productId = 1L;
        Product product = ProductUtil.buildProduct();
        product.setId(productId);
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        try {
            ProductDto result = productService.findProductById(productId);
            fail("Should throw exception");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), String.format("%s not found with the given input data %s : '%s'", "Product", "id", productId));
        }
    }
}
