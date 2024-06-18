package com.xiao.products.controller;

import com.xiao.products.constants.ProductConstants;
import com.xiao.products.dto.ProductDto;
import com.xiao.products.dto.ProductUpdateDto;
import com.xiao.products.dto.ResponseDto;
import com.xiao.products.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody ProductDto productDto) {
        productService.createProduct(productDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(ProductConstants.STATUS_201, ProductConstants.MESSAGE_201));
    }

    @GetMapping
    public ResponseEntity<Page<ProductDto>> getProducts(
            @RequestParam(defaultValue = "0") int pages,
            @RequestParam(defaultValue = "10") int pageSize
    ){
        Page<ProductDto> productDtosPage = productService.findAllProducts(pages, pageSize);
        return ResponseEntity
                .status(200)
                .body(productDtosPage);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id){
        ProductDto productDto = productService.findProductById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productDto);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(Long id){
        productService.deleteProduct(id);
        return ResponseEntity
                .ok().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long id, @Valid @RequestBody ProductUpdateDto productDto){
        ProductDto productDtoResponse = productService.updateProduct(id, productDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productDtoResponse);
    }
}
