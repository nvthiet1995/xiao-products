package com.xiao.products.dto;

import com.xiao.products.entity.Product;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchDto {

    private Long id;

    @Size(min = 2, max = 255, message = "The name must have a minimum of 2 characters and a maximum of 250 characters")
    private String name;

    @Size(min = 5, max = 1000, message = "The logo must have a minimum of 5 characters and a maximum of 1000 characters")
    private String logo;

    @Size(max = 1000, message = "The description must have a maximum of 1000 characters")
    private String description;

    @Size(max = 100, message = "The country must have a maximum of 100 characters")
    private String country;

    private Product product;
}
