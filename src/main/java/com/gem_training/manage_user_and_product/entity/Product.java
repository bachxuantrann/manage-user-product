package com.gem_training.manage_user_and_product.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gem_training.manage_user_and_product.dto.ProductDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {
    @NotBlank(message = "name of product is required")
    private String name;
    @NotNull(message = "price of product is required")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

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
        }// thừa phần này
        return productDTO;
    }
}
