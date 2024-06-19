package com.xiao.products.dto;

import com.xiao.products.entity.Product;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageDto {

    private Long id;

    @Size(max = 255, message = "The image name must be less than 255 characters")
    @Nullable
    private String name;

    @Size(min = 5, max = 1000, message = "The link must have a minimum of 5 characters and a maximum of 1000 characters")
    private String link;

    private Product product;
}
