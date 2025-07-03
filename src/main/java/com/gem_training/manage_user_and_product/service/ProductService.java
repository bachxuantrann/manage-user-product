package com.gem_training.manage_user_and_product.service;

import com.gem_training.manage_user_and_product.dto.ProductDTO;
import com.gem_training.manage_user_and_product.exception.IdInvalidException;

import java.util.List;

public interface ProductService  {
    ProductDTO handleGetProduct(Long id) throws IdInvalidException;

    ProductDTO handleCreateProduct(ProductDTO productDTO) throws IdInvalidException ;

    void handleDeleteProduct(Long id) throws IdInvalidException;

    ProductDTO handleUpdateProduct(ProductDTO productDTO) throws IdInvalidException;

    List<ProductDTO> handleGetAllProducts(String keyword);

}
