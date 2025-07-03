package com.gem_training.manage_user_and_product.controller;

import com.gem_training.manage_user_and_product.dto.UserDTO;
import com.gem_training.manage_user_and_product.entity.User;
import com.gem_training.manage_user_and_product.exception.IdInvalidException;
import com.gem_training.manage_user_and_product.service.UserService;
import com.gem_training.manage_user_and_product.util.annotation.ApiMessage;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    @ApiMessage("create a new user")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody User user) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.handleCreateUser(user));
    }
    @GetMapping("/detail/{id}")
    @ApiMessage("get info detail of user")
    public ResponseEntity<UserDTO> getUserDetail(@PathVariable Long id) throws IdInvalidException {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.handleGetUserDetail(id));
    }
    @DeleteMapping("/delete/{id}")
    @ApiMessage("delete a user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws IdInvalidException{
        this.userService.handleDeleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
