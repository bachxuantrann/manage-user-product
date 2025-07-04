package com.gem_training.manage_user_and_product.controller;

import com.gem_training.manage_user_and_product.dto.ProductDTO;
import com.gem_training.manage_user_and_product.dto.ResultPaginationDTO;
import com.gem_training.manage_user_and_product.entity.Product;
import com.gem_training.manage_user_and_product.exception.IdInvalidException;
import com.gem_training.manage_user_and_product.service.ProductService;
import com.gem_training.manage_user_and_product.util.annotation.ApiMessage;
import com.turkraft.springfilter.boot.Filter;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    @ApiMessage("create a product")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) throws IdInvalidException {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.handleCreateProduct(productDTO));
    }

    @DeleteMapping("/delete/{id}")
    @ApiMessage("delete a product")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws IdInvalidException {
        this.productService.handleDeleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/detail/{id}")
    @ApiMessage("get detail of product")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) throws IdInvalidException {
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.handleGetProduct(id));
    }

    @PutMapping("/update")
    @ApiMessage("update a product")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO) throws IdInvalidException {
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.handleUpdateProduct(productDTO));
    }

    @GetMapping("/getAll")
    @ApiMessage("get all products")
    public ResponseEntity<ResultPaginationDTO> getAllProducts(@Filter Specification<Product> spec, Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.handleGetAllProducts(spec, pageable));
    }
}
