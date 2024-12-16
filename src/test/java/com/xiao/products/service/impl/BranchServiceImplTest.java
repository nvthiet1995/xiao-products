package com.xiao.products.service.impl;

import com.xiao.products.dto.BranchDto;
import com.xiao.products.entity.Branch;
import com.xiao.products.mapper.BranchMapper;
import com.xiao.products.repository.BranchRepository;
import com.xiao.products.util.BranchUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BranchServiceImplTest {

    @Mock
    private BranchRepository branchRepository;

    private final BranchMapper branchMapper = Mappers.getMapper(BranchMapper.class);

    private BranchServiceImpl branchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        branchService = new BranchServiceImpl(branchRepository, branchMapper);
    }

    @Test
    void testCreateBranch(){
        BranchDto branchDto = BranchUtil.buildBranchDto();
        branchService.createBranch(branchDto);

        verify(branchRepository, times(1)).save(any(Branch.class));
    }

    @Test
    void testCreateBranch_whenBranchRepositoryGotException(){
        BranchDto branchDto = BranchUtil.buildBranchDto();
        when(branchRepository.save(any(Branch.class))).thenThrow(new RuntimeException("Exception"));

        try{
            branchService.createBranch(branchDto);
            fail("Should throw exception");
        }catch (RuntimeException e){
            assertEquals(e.getMessage(), "Exception");
        }

        verify(branchRepository, times(1)).save(any(Branch.class));
    }

    @Test
    void testFindBranchById() {
        Long branchId = 1L;
        Branch branch = BranchUtil.buildBranch();
        branch.setId(branchId);
        when(branchRepository.findById(branchId)).thenReturn(Optional.of(branch));

        BranchDto result = branchService.findBranchById(branchId);

        assertEquals(branch.getName(), result.getName());
        assertEquals(branch.getId(), result.getId());
        assertEquals(branch.getDescription(), result.getDescription());
        assertEquals(branch.getCountry(), result.getCountry());
        assertEquals(branch.getLogo(), result.getLogo());
    }

    @Test
    void testFindBranchById_whenNotFound() {
        Long branchId = 1L;
        Branch branch = BranchUtil.buildBranch();
        branch.setId(branchId);
        when(branchRepository.findById(branchId)).thenReturn(Optional.empty());

        try {
            BranchDto result = branchService.findBranchById(branchId);
            fail("Should throw exception");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), String.format("%s not found with the given input data %s : '%s'", "Branch", "id", branchId));
        }
    }
}
