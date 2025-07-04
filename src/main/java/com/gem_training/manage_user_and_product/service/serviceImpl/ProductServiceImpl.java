package com.gem_training.manage_user_and_product.service.serviceImpl;

import com.gem_training.manage_user_and_product.dto.MetaDTO;
import com.gem_training.manage_user_and_product.dto.ProductDTO;
import com.gem_training.manage_user_and_product.dto.ResultPaginationDTO;
import com.gem_training.manage_user_and_product.entity.Category;
import com.gem_training.manage_user_and_product.entity.Product;
import com.gem_training.manage_user_and_product.exception.IdInvalidException;
import com.gem_training.manage_user_and_product.repository.CategoryRepository;
import com.gem_training.manage_user_and_product.repository.ProductRepository;
import com.gem_training.manage_user_and_product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductDTO handleGetProduct(Long id) throws IdInvalidException {
        return this.productRepository.findById(id).orElseThrow(() -> new IdInvalidException("category does not exist")).toProductDTO();
    }

    @Override
    public ProductDTO handleCreateProduct(ProductDTO productDTO) throws IdInvalidException {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        if (productDTO.getCategoryId() != null) {
            Category category = this.categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(
                    () -> new IdInvalidException("category does not exist"));
            product.setCategory(category);
        } else {
            product.setCategory(null);
        }
        return this.productRepository.save(product).toProductDTO();

    }

    @Override
    public void handleDeleteProduct(Long id) throws IdInvalidException {
        Product product = this.productRepository.findById(id).orElseThrow(() -> new IdInvalidException("product does not exist"));
        this.productRepository.delete(product);
    }

    @Override
    public ProductDTO handleUpdateProduct(ProductDTO productDTO) throws IdInvalidException {
        Product currentProduct = this.productRepository.findById(productDTO.getId()).orElseThrow(
                () -> new IdInvalidException("product does not exist")
        );
        if (productDTO.getName() != null) {
            currentProduct.setName(productDTO.getName());
        }
        if (productDTO.getPrice() != null) {
            currentProduct.setPrice(productDTO.getPrice());
        }
        if (productDTO.getCategoryId() != null) {
            currentProduct.setCategory(this.categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(
                    () -> new IdInvalidException("category does not exist")
            ));
        }
        return this.productRepository.save(currentProduct).toProductDTO();
    }

    @Override
    public ResultPaginationDTO handleGetAllProducts(Specification spec, Pageable pageable) {
        Page<Product> products = this.productRepository.findAll(spec,pageable);
        ResultPaginationDTO result =  new ResultPaginationDTO();
        MetaDTO metaDTO = new MetaDTO();
        metaDTO.setPage(pageable.getPageNumber()+1);
        metaDTO.setPageSize(pageable.getPageSize()+1);
        metaDTO.setTotal(products.getTotalElements());
        metaDTO.setTotalPages(products.getTotalPages());
        result.setMetaDTO(metaDTO);
        result.setResult(products.getContent());
        return result;
    }

}
