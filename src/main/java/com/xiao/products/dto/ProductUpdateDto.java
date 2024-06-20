package com.xiao.products.dto;

import com.xiao.products.validator.CheckAllValueEmpty;
import lombok.*;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@CheckAllValueEmpty(fields = {"name", "description", "leftInStock", "specifications"}, message = "Please enter at least one piece when updating the product")
public class ProductUpdateDto {
    private Long id;

    private String name;

    private String description;

    private Integer leftInStock;

    private Map<String, String> specifications;

    private Set<ProductImageDto> productImages;
}
