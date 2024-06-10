package com.xiao.products.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;

    @NotEmpty
    @Size(min = 2, max = 500)
    private String name;

    @NotEmpty
    @Size(min = 2, max = 10000)
    private String description;

    @NotEmpty
    private Integer leftInStock;

    private Set<ProductDetailDto> productDetails = new HashSet<>();
}
