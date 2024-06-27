package com.xiao.products.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;

    @NotEmpty(message = "Name can't be empty")
    @Size(min = 2, max = 500, message = "Name must have at least 2 characters and be less than 500 characters.")
    private String name;

    @NotEmpty(message = "Description can't be empty")
    @Size(min = 2, max = 10000, message = "Description must have at least 2 characters and be less than 10,000 characters.")
    private String description;

    @Min(value = 1, message = "The remaining quantity of products must be at least 1.")
    private Integer leftInStock;

    private Map<String, String> specifications;

    private Set<ProductImageDto> productImages;

    private BranchDto branch;
}
