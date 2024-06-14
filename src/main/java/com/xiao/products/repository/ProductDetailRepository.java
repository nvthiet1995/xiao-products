package com.xiao.products.repository;

import com.xiao.products.entity.Product;
import com.xiao.products.entity.ProductDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
    @Transactional
    void deleteByProductId(Long productId);
}
