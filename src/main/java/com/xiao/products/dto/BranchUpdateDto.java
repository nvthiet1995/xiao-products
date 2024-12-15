package com.xiao.products.dto;

import com.xiao.products.entity.Product;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchUpdateDto {

    private Long id;

    private String name;

    private String logo;

    private String description;

    private String country;

}
