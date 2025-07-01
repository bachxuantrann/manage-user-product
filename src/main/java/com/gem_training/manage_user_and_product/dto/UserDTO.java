package com.gem_training.manage_user_and_product.dto;

import com.gem_training.manage_user_and_product.util.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String address;
    private RoleEnum role;
    private Instant createdAt;
    private Instant updatedAt;
}
