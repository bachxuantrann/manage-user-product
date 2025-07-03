package com.gem_training.manage_user_and_product.service.serviceImpl;

import com.gem_training.manage_user_and_product.dto.CategoryDTO;
import com.gem_training.manage_user_and_product.dto.ProductDTO;
import com.gem_training.manage_user_and_product.entity.Category;
import com.gem_training.manage_user_and_product.exception.IdInvalidException;
import com.gem_training.manage_user_and_product.repository.CategoryRepository;
import com.gem_training.manage_user_and_product.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDTO handleCreateCategory(CategoryDTO categoryDTO) throws IdInvalidException {
        boolean isExist = isCategoryExist(categoryDTO.getName());
        if (isExist) {
            throw new IdInvalidException("category already exist");
        }
        Category category = new Category();
        category.setName(categoryDTO.getName());
        return this.categoryRepository.save(category).toCategoryDTO();
    }

    @Override
    public void handleDeleteCategory(Long id) throws IdInvalidException {
        Category category = this.categoryRepository.findById(id).orElseThrow(
                () -> new IdInvalidException("category does not exist")
        );
        if (category != null) {
            this.categoryRepository.delete(category);
        }
    }

    @Override
    public CategoryDTO handleUpdateCategory(CategoryDTO categoryDTO) throws IdInvalidException {
        Category currentCategory = this.categoryRepository.findById(categoryDTO.getId()).orElseThrow(
                () -> new IdInvalidException("category does not exist"));
        currentCategory.setName(categoryDTO.getName());
        return this.categoryRepository.save(currentCategory).toCategoryDTO();
    }

    @Override
    public CategoryDTO handleGetCategory(Long id) throws IdInvalidException {
        return this.categoryRepository.findById(id).orElseThrow(
                ()-> new IdInvalidException("category is not exist")
        ).toCategoryDTO();
    }

    @Override
    public List<ProductDTO> handleGetAllProductsOfCategory(String keyword) {
        return List.of();
    }

    @Override
    public List<CategoryDTO> handleGetAllCategories() {
        return List.of();
    }

    public boolean isCategoryExist(String name) {
        if (this.categoryRepository.findByName(name).isPresent()) {
            return true;
        }
        return false;
    }
}
