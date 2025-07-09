package com.gem_training.manage_user_and_product.service;

import com.gem_training.manage_user_and_product.dto.ResultPaginationDTO;
import com.gem_training.manage_user_and_product.dto.UserDTO;
import com.gem_training.manage_user_and_product.entity.User;
import com.gem_training.manage_user_and_product.exception.IdInvalidException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface UserService {
    UserDTO handleCreateUser(User user) throws IdInvalidException;

    UserDTO handleGetUserDetail(Long id) throws IdInvalidException;

    void handleDeleteUser(Long id) throws IdInvalidException;

    UserDTO handleUpdateUser(User user) throws IdInvalidException;

    ResultPaginationDTO handleGetAllUsers(Specification spec, Pageable pageable);

    String getUsernameById(Long id) throws IdInvalidException;
}
