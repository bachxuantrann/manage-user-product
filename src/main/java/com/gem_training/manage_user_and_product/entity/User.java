package com.gem_training.manage_user_and_product.entity;

import com.gem_training.manage_user_and_product.dto.UserDTO;
import com.gem_training.manage_user_and_product.util.SecurityUtil;
import com.gem_training.manage_user_and_product.util.enums.RoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "username is required")
    private String username;
    @NotBlank(message = "password is required")
    private String password;
    private int age;
    private String address;
    @Enumerated(EnumType.STRING)
    private RoleEnum role;
    private Instant createdAt;
    private Instant updatedAt;
    private String createdBy;
    private String updatedBy;


    public UserDTO toUserDTO() {
        return new UserDTO(
                this.id, this.username, this.address, this.role.name(), this.createdAt, this.updatedAt
        );
    }

    @PrePersist
    public void handleBeforeCreate() {
        this.createdBy = SecurityUtil.getCurrentUserLogin();
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void handleBeforeUpdate() {
        this.updatedBy = SecurityUtil.getCurrentUserLogin();
        this.updatedAt = Instant.now();
    }
}
