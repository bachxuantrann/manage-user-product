package com.gem_training.manage_user_and_product.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gem_training.manage_user_and_product.dto.CategoryDTO;
import com.gem_training.manage_user_and_product.util.annotation.Pattern;
import com.gem_training.manage_user_and_product.util.annotation.Validator;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseEntity<CategoryDTO> {
   //@NotBlank(message = "name of category is required")
    @Validator(required = true, messageCode = "category.name.invalid", patterns = {
            @Pattern(pattern = "^[A-Za-z\\s]+$", messageCode = "category.invalid")
    })
    private String name;
    //    one category has many products
    @OneToMany(mappedBy = "category")
    @JsonManagedReference
    private List<Product> products = new ArrayList<>();

}
