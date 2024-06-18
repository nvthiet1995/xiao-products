package com.xiao.products.entity;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.HashMap;
import java.util.Map;

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

    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Map<String, String> specifications = new HashMap<>();
}
