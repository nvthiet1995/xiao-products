package com.xiao.products.util;

import com.xiao.products.dto.BranchDto;
import com.xiao.products.dto.BranchDto;
import com.xiao.products.entity.Branch;

import java.util.HashMap;
import java.util.Map;

public class BranchUtil {
    public static BranchDto buildBranchDto(){
        return BranchDto.builder()
                .name("Apple")
                .country("USA")
                .logo("https://fastly.picsum.photos/id/4/5000/3333.jpg?hmac=ghf06FdmgiD0-G4c9DdNM8RnBIN7BO0-ZGEw47khHP4")
                .description("new branch")
                .build();
    }

    public static Branch buildBranch(){
        return Branch.builder()
                .name("Apple")
                .country("USA")
                .logo("https://fastly.picsum.photos/id/4/5000/3333.jpg?hmac=ghf06FdmgiD0-G4c9DdNM8RnBIN7BO0-ZGEw47khHP4")
                .description("new branch")
                .build();
    }

    public static BranchDto buildBranchUpdateDto(){
        return BranchDto.builder()
                .name("Apple")
                .country("USA")
                .logo("https://fastly.picsum.photos/id/4/5000/3333.jpg?hmac=ghf06FdmgiD0-G4c9DdNM8RnBIN7BO0-ZGEw47khHP4")
                .description("new branch")
                .build();
    }
}
