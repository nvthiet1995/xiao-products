package com.xiao.products.controller;

import com.xiao.products.constants.ProductConstants;
import com.xiao.products.dto.ProductDto;
import com.xiao.products.dto.ResponseDto;
import com.xiao.products.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final IProductService iProductService;

    public ProductController(IProductService iProductService) {
        this.iProductService = iProductService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody ProductDto productDto) {
        iProductService.createProduct(productDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(ProductConstants.STATUS_201, ProductConstants.MESSAGE_201));
    }
}
