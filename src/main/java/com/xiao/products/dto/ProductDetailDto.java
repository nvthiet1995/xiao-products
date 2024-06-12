package com.xiao.products.dto;

import com.xiao.products.entity.Product;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailDto {
    private Long id;

    @NotEmpty
    @Size(min = 2, max = 255)
    private String name;

    @NotEmpty
    @Size(min = 2, max = 500)
    private String value;

    private ProductDto product;
}
