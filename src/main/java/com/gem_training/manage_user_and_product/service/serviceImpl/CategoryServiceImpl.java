package com.gem_training.manage_user_and_product.service.serviceImpl;

import com.gem_training.manage_user_and_product.dto.CategoryDTO;
import com.gem_training.manage_user_and_product.dto.MetaDTO;
import com.gem_training.manage_user_and_product.dto.ResultPaginationDTO;
import com.gem_training.manage_user_and_product.entity.Category;
import com.gem_training.manage_user_and_product.exception.IdInvalidException;
import com.gem_training.manage_user_and_product.repository.CategoryRepository;
import com.gem_training.manage_user_and_product.service.CategoryService;
import com.gem_training.manage_user_and_product.util.ValidatorUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDTO handleCreateCategory(CategoryDTO categoryDTO) throws IdInvalidException, IllegalAccessException {
        boolean isExist = isCategoryExist(categoryDTO.getName());
//        if (isExist) {
//            throw new IdInvalidException("category already exist");
//        }
        Category category = new Category();
        category.setName(categoryDTO.getName());
        if (ValidatorUtil.validate(category)) {
            return this.categoryRepository.save(category).toDTO(CategoryDTO.class);
        }
        return null;
    }

    @Override
    public void handleDeleteCategory(Long id) throws IdInvalidException {
        Category category = this.categoryRepository.findById(id).orElseThrow(
                () -> new IdInvalidException("category does not exist")
        );
        this.categoryRepository.delete(category);
    }

    @Override
    public CategoryDTO handleUpdateCategory(CategoryDTO categoryDTO) throws IdInvalidException {
        Category currentCategory = this.categoryRepository.findById(categoryDTO.getId()).orElseThrow(
                () -> new IdInvalidException("category does not exist"));
        currentCategory.setName(categoryDTO.getName());
        return this.categoryRepository.save(currentCategory).toDTO(CategoryDTO.class);
    }

    @Override
    public CategoryDTO handleGetCategory(Long id) throws IdInvalidException {
        return this.categoryRepository.findById(id).orElseThrow(
                () -> new IdInvalidException("category is not exist")
        ).toDTO(CategoryDTO.class);
    }


    @Override
    public ResultPaginationDTO handleGetAllCategories(Specification<Category> spec, Pageable pageable) {
        Page<Category> categories = this.categoryRepository.findAll(spec, pageable);
        ResultPaginationDTO result = new ResultPaginationDTO();
        MetaDTO metaDTO = new MetaDTO();
        metaDTO.setPage(pageable.getPageNumber() + 1);
        metaDTO.setPageSize(pageable.getPageSize() + 1);
        metaDTO.setTotal(categories.getTotalElements());
        metaDTO.setTotalPages(categories.getTotalPages());
        result.setMetaDTO(metaDTO);
        result.setResult(categories.getContent());
        return result;
    }

    public boolean isCategoryExist(String name) {
        return this.categoryRepository.findByName(name).isPresent();
    }
}
