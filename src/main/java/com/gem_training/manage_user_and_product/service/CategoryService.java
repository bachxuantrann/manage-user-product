package com.gem_training.manage_user_and_product.service;

import com.gem_training.manage_user_and_product.dto.CategoryDTO;
import com.gem_training.manage_user_and_product.dto.ProductDTO;
import com.gem_training.manage_user_and_product.exception.IdInvalidException;

import java.util.List;

public interface CategoryService {
    CategoryDTO handleCreateCategory(CategoryDTO categoryDTO) throws IdInvalidException;

    void handleDeleteCategory(Long id) throws IdInvalidException;

    CategoryDTO handleUpdateCategory(CategoryDTO categoryDTO) throws IdInvalidException;

    CategoryDTO handleGetCategory(Long id) throws IdInvalidException;

    List<ProductDTO> handleGetAllProductsOfCategory(String keyword);

    List<CategoryDTO> handleGetAllCategories();
}
