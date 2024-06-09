package com.xiao.products.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailsDto {
    @NotEmpty
    @Size(min = 2, max = 255)
    private String name;

    @NotEmpty
    @Size(min = 2, max = 500)
    private String value;
}
