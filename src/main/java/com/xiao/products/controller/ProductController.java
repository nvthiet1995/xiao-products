package com.xiao.products.controller;

import com.xiao.products.constants.ProductConstants;
import com.xiao.products.dto.ProductDto;
import com.xiao.products.dto.ResponseDto;
import com.xiao.products.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<Page<ProductDto>> getProducts(
            @RequestParam(defaultValue = "0") int pages,
            @RequestParam(defaultValue = "10") int pageSize
    ){
        Page<ProductDto> productDtosPage = iProductService.findAllProducts(pages, pageSize);
        return ResponseEntity
                .status(200)
                .body(productDtosPage);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id){
        ProductDto productDto = iProductService.findProductById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productDto);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(Long id){
        iProductService.deleteProduct(id);
        return ResponseEntity
                .ok().build();
    }
}
