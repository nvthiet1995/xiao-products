package com.xiao.products.service.impl;

import com.xiao.products.dto.BranchDto;
import com.xiao.products.dto.BranchUpdateDto;
import com.xiao.products.entity.Branch;
import com.xiao.products.exception.ResourceNotFoundException;
import com.xiao.products.mapper.BranchMapper;
import com.xiao.products.repository.BranchRepository;
import com.xiao.products.service.BranchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    public BranchServiceImpl(BranchRepository branchRepository, BranchMapper branchMapper) {
        this.branchRepository = branchRepository;
        this.branchMapper = branchMapper;
    }

    @Override
    public void createBranch(BranchDto branchDto) {
        branchRepository.save(branchMapper.branchDtoToBranch(branchDto));
    }

    @Override
    public BranchDto findBranchById(Long id) {
        Branch branch = branchRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Branch", "id", String.valueOf(id))
        );
        return branchMapper.branchToBranchDto(branch);
    }

    @Override
    public Page<BranchDto> findAllBranches(int pages, int pageSize) {
        Page<Branch> branch = branchRepository.findAll(PageRequest.of(pages, pageSize));
        return branch.map(branchMapper::branchToBranchDto);
    }

    @Override
    public BranchDto updateBranch(Long id, BranchUpdateDto branchUpdateDto) {
        Branch existingBranch = branchRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product", "id", String.valueOf(id))
        );

        existingBranch = mapValueFieldUpdate(existingBranch, branchUpdateDto);

        return branchMapper.branchToBranchDto(branchRepository.save(existingBranch));
    }

    @Override
    public void deleteBranch(Long id) {
        Branch branch = branchRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("branch", "id", String.valueOf(id)));
        branchRepository.delete(branch);
    }

    private Branch mapValueFieldUpdate(Branch existingBranch, BranchUpdateDto branchDto) {
        return Branch.builder()
                .id(existingBranch.getId())
                .name(Objects.isNull(branchDto.getName()) || branchDto.getName().isEmpty() ? existingBranch.getName() : branchDto.getName())
                .description(Objects.isNull(branchDto.getDescription()) || branchDto.getDescription().isEmpty() ? existingBranch.getDescription() : branchDto.getDescription())
                .logo(Objects.isNull(branchDto.getLogo()) ? existingBranch.getLogo() : branchDto.getLogo())
                .country(branchDto.getCountry().isEmpty() ? existingBranch.getCountry() : branchDto.getCountry())
                .build();
    }

}
