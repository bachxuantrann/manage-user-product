package com.gem_training.manage_user_and_product.repository;

import com.gem_training.manage_user_and_product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long>, JpaSpecificationExecutor<Category> {
    Optional<Category> findByName(String name);
}
