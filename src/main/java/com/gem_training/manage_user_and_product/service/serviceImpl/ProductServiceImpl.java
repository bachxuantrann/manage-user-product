package com.gem_training.manage_user_and_product.service.serviceImpl;

import com.gem_training.manage_user_and_product.dto.ProductDTO;
import com.gem_training.manage_user_and_product.exception.IdInvalidException;
import com.gem_training.manage_user_and_product.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public ProductDTO handleGetProduct(Long id) throws IdInvalidException {
        return null;
    }

    @Override
    public ProductDTO handleCreateProduct(ProductDTO productDTO) throws IdInvalidException {
        return null;
    }

    @Override
    public void handleDeleteProduct(Long id) throws IdInvalidException {

    }

    @Override
    public ProductDTO handleUpdateProduct(ProductDTO productDTO) throws IdInvalidException {
        return null;
    }

    @Override
    public List<ProductDTO> handleGetAllProducts(String keyword) {
        return List.of();
    }
}
