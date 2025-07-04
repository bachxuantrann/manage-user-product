package com.gem_training.manage_user_and_product.entity;

import com.gem_training.manage_user_and_product.dto.UserDTO;
import com.gem_training.manage_user_and_product.util.enums.RoleEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity<UserDTO> {
    @NotBlank(message = "username is required")
    private String username;
    @NotBlank(message = "password is required")
    private String password;
    private int age;
    private String address;
    @Enumerated(EnumType.STRING)
    private RoleEnum role;
}
