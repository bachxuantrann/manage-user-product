package com.gem_training.manage_user_and_product.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gem_training.manage_user_and_product.dto.ProductDTO;
import com.gem_training.manage_user_and_product.util.SecurityUtil;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "name of product is required")
    private String name;
    @NotNull(message = "price of product is required")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

    private Instant createdAt;
    private Instant updatedAt;
    private String createdBy;
    private String updatedBy;

    @PrePersist
    public void handleBeforeCreate() {
        this.createdAt = Instant.now();
        this.createdBy = SecurityUtil.getCurrentUserLogin();
    }

    @PreUpdate
    public void handleBeforeUpdate() {
        this.updatedBy = SecurityUtil.getCurrentUserLogin();
        this.updatedAt = Instant.now();
    }


    public ProductDTO toProductDTO() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(this.id);
        productDTO.setName(this.name);
        productDTO.setPrice(this.price);
        productDTO.setCreatedAt(this.createdAt);
        productDTO.setUpdatedAt(this.updatedAt);
        if (this.category != null) {
            productDTO.setCategory(this.category.getName());
            productDTO.setCategoryId(this.category.getId());
        } else {
            productDTO.setCategory(null);
            productDTO.setCategoryId(null);
        }
        return productDTO;
    }
}
