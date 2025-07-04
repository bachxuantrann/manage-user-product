package com.gem_training.manage_user_and_product.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gem_training.manage_user_and_product.dto.CategoryDTO;
import com.gem_training.manage_user_and_product.util.SecurityUtil;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "name of category is required")
    private String name;
    //    one category has many products
    @OneToMany(mappedBy = "category")
    @JsonManagedReference
    private List<Product> products = new ArrayList<>();

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

    public CategoryDTO toCategoryDTO() {
        return new CategoryDTO(this.id, this.name, this.createdAt, this.updatedAt);
    }
}
