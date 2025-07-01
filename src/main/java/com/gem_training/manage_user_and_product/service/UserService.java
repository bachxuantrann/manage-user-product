package com.gem_training.manage_user_and_product.service;

import com.gem_training.manage_user_and_product.dto.UserDTO;
import com.gem_training.manage_user_and_product.entity.User;
import com.gem_training.manage_user_and_product.exception.IdInvalidException;

public interface UserService {
    UserDTO handleCreateUser(User user) throws IdInvalidException;
    UserDTO handleGetUserDetail(Long id) throws IdInvalidException;
}
