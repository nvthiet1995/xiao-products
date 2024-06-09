package com.xiao.products.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 500)
    private String name;

    @Column(name = "description", nullable = false ,length = 10000)
    private String description;

    @Column(name = "left_in_stock", nullable = false, length = 10)
    private Integer leftInStock;

    @OneToMany
    @JoinColumn(name = "product_details")
    private Set<ProductDetail> productDetails = new HashSet<>();
}
