package com.gem_training.manage_user_and_product.service;

import com.gem_training.manage_user_and_product.dto.ProductDTO;
import com.gem_training.manage_user_and_product.dto.ResultPaginationDTO;
import com.gem_training.manage_user_and_product.entity.Product;
import com.gem_training.manage_user_and_product.exception.IdInvalidException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ProductService  {
    ProductDTO handleGetProduct(Long id) throws IdInvalidException;

    ProductDTO handleCreateProduct(ProductDTO productDTO) throws IdInvalidException ;

    void handleDeleteProduct(Long id) throws IdInvalidException;

    ProductDTO handleUpdateProduct(ProductDTO productDTO) throws IdInvalidException;

    ResultPaginationDTO handleGetAllProducts(Specification<Product> spec, Pageable pageable);

}
