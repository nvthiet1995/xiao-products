package com.xiao.products.service;

import com.xiao.products.dto.BranchDto;
import com.xiao.products.dto.BranchUpdateDto;
import com.xiao.products.dto.ProductDto;
import org.springframework.data.domain.Page;

public interface BranchService {

    void createBranch(BranchDto branchDto);

    BranchDto findBranchById(Long id);

    Page<BranchDto> findAllBranches(int pages, int pageSize);

    BranchDto updateBranch(Long id, BranchUpdateDto productDto);

    void deleteBranch(Long id);
}
