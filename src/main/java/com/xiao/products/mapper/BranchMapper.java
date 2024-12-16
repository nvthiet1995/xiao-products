package com.xiao.products.mapper;

import com.xiao.products.dto.BranchDto;
import com.xiao.products.entity.Branch;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    BranchDto branchToBranchDto(Branch branch);

    Branch branchDtoToBranch(BranchDto branchDto);

}
