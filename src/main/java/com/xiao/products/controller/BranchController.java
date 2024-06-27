package com.xiao.products.controller;

import com.xiao.products.constants.ProductConstants;
import com.xiao.products.dto.BranchDto;
import com.xiao.products.dto.BranchUpdateDto;
import com.xiao.products.dto.ResponseDto;
import com.xiao.products.service.BranchService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/branches")
public class BranchController {
    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody BranchDto branchDto) {
        branchService.createBranch(branchDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(ProductConstants.STATUS_201, ProductConstants.MESSAGE_201));
    }

    @GetMapping
    public ResponseEntity<Page<BranchDto>> getProducts(
            @RequestParam(defaultValue = "0") int pages,
            @RequestParam(defaultValue = "10") int pageSize
    ){
        Page<BranchDto> branchDtosPage = branchService.findAllBranches(pages, pageSize);
        return ResponseEntity
                .status(200)
                .body(branchDtosPage);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BranchDto> getProductById(@PathVariable("id") Long id){
        BranchDto branchDto = branchService.findBranchById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(branchDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id){
        branchService.deleteBranch(id);
        return ResponseEntity
                .ok().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<BranchDto> updateProduct(@PathVariable("id") Long id, @Valid @RequestBody BranchUpdateDto branchUpdateDto){
        BranchDto branchDtoResponse = branchService.updateBranch(id, branchUpdateDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(branchDtoResponse);
    }
}
