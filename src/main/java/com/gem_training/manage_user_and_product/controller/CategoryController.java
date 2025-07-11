package com.gem_training.manage_user_and_product.controller;

import com.gem_training.manage_user_and_product.dto.CategoryDTO;
import com.gem_training.manage_user_and_product.dto.ResultPaginationDTO;
import com.gem_training.manage_user_and_product.entity.Category;
import com.gem_training.manage_user_and_product.exception.IdInvalidException;
import com.gem_training.manage_user_and_product.service.CategoryService;
import com.gem_training.manage_user_and_product.util.annotation.ApiMessage;
import com.turkraft.springfilter.boot.Filter;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    @ApiMessage("create a category")
    @Operation(summary = "Create a category",description = "Need only role ADMIN to create a new category")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) throws IdInvalidException {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.categoryService.handleCreateCategory(categoryDTO));
    }

    @DeleteMapping("/delete/{id}")
    @ApiMessage("delete a category")
    @Operation(summary = "Delete a category", description = "Need only role ADMIN to delete a category")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) throws IdInvalidException {
        this.categoryService.handleDeleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/detail/{id}")
    @ApiMessage("get a category")
    @Operation(summary = "get a category",description = "get detail info of category")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long id) throws IdInvalidException {
        return ResponseEntity.status(HttpStatus.OK).body(this.categoryService.handleGetCategory(id));
    }

    @PutMapping("/update")
    @ApiMessage("update a category")
    @Operation(summary = "update a category",description = "update info of category")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO) throws IdInvalidException {
        return ResponseEntity.status(HttpStatus.OK).body(this.categoryService.handleUpdateCategory(categoryDTO));
    }

    @GetMapping("/getAll")
    @ApiMessage("get all category")
    @Operation(summary = "get all category",description = "Get and Find category and pagination,sort")
    public ResponseEntity<ResultPaginationDTO> getAllCategories(@Filter Specification<Category> spec, Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(this.categoryService.handleGetAllCategories(spec, pageable));
    }
}
