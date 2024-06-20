package com.xiao.products.service.impl;

import com.xiao.products.dto.ProductDto;
import com.xiao.products.dto.ProductUpdateDto;
import com.xiao.products.entity.Product;
import com.xiao.products.exception.ResourceNotFoundException;
import com.xiao.products.mapper.ProductMapper;
import com.xiao.products.repository.ProductRepository;
import com.xiao.products.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public void createProduct(ProductDto productDto) {
        Product product = productMapper.productDtoToProduct(productDto);
        if(product.getProductImages() != null && !product.getProductImages().isEmpty()) {
            mappingProductIntoProductImages(product);
        }
        productRepository.save(product);
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
    public ProductDto updateProduct(Long id, ProductUpdateDto productDto) {
        Product existingProduct = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product", "id", String.valueOf(id))
        );

        existingProduct = mapValueFieldUpdate(existingProduct, productDto);

        return productMapper.productToProductDto(productRepository.save(existingProduct));
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("product", "id", String.valueOf(id)));
        productRepository.delete(product);
    }

    private Product mapValueFieldUpdate(Product existingProduct, ProductUpdateDto productDto) {
        Product product = Product.builder()
                .id(existingProduct.getId())
                .name(Objects.isNull(productDto.getName()) || productDto.getName().isEmpty() ? existingProduct.getName() : productDto.getName())
                .description(Objects.isNull(productDto.getDescription()) || productDto.getDescription().isEmpty() ? existingProduct.getDescription() : productDto.getDescription())
                .leftInStock(Objects.isNull(productDto.getLeftInStock()) ? existingProduct.getLeftInStock() : productDto.getLeftInStock())
                .specifications(productDto.getSpecifications().isEmpty() ? existingProduct.getSpecifications() : productDto.getSpecifications())
                .build();

        return product;
    }

    private void mappingProductIntoProductImages(Product product){
        product.getProductImages().forEach(productImage -> {
            productImage.setProduct(product);
        });
    }
}
