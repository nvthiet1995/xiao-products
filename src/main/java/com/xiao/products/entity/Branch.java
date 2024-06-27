package com.xiao.products.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "product_images")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Branch extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "logo", nullable = false)
    private String logo;

    @Column(name = "description")
    private String description;

    @Column(name = "country")
    private String country;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    private Set<Product> products;
}
