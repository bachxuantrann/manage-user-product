package com.gem_training.manage_user_and_product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
    //  Name of category
    private String category;
    //  Id of category
    private Long categoryId;
    private Instant createdAt;
    private Instant updatedAt;
}
