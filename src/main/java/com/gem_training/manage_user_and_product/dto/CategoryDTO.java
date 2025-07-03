package com.gem_training.manage_user_and_product.dto;

import com.gem_training.manage_user_and_product.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private Instant createdAt;
    private Instant updatedAt;
}
