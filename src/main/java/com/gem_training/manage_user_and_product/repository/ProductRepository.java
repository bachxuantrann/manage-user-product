package com.gem_training.manage_user_and_product.repository;

import com.gem_training.manage_user_and_product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByName(String name);

    List<Product> id(Long id);
}
