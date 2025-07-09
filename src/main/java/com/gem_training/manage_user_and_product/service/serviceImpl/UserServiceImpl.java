package com.gem_training.manage_user_and_product.service.serviceImpl;

import com.gem_training.manage_user_and_product.dto.MetaDTO;
import com.gem_training.manage_user_and_product.dto.ResultPaginationDTO;
import com.gem_training.manage_user_and_product.dto.UserDTO;
import com.gem_training.manage_user_and_product.entity.User;
import com.gem_training.manage_user_and_product.exception.IdInvalidException;
import com.gem_training.manage_user_and_product.repository.UserRepository;
import com.gem_training.manage_user_and_product.service.UserService;
import com.gem_training.manage_user_and_product.util.enums.RoleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO handleCreateUser(User user) throws IdInvalidException {
        boolean isExist = this.isUserExist(user.getUsername().trim());
        if (isExist) {
            throw new IdInvalidException("user is exist");
        }
        String password = user.getPassword().trim();
        String hassPassword = this.passwordEncoder.encode(password);
        user.setPassword(hassPassword);
        user.setRole(RoleEnum.USER);
        User newUser = this.userRepository.save(user);
        return newUser.toDTO(UserDTO.class);
    }

    @Override
    public UserDTO handleGetUserDetail(Long id) throws IdInvalidException {
        return this.userRepository.findById(id).orElseThrow(
                () -> new IdInvalidException("user is not exist")
        ).toDTO(UserDTO.class);
    }

    public boolean isUserExist(String userName) {
        return this.userRepository.findByUsername(userName.trim()).isPresent();
    }

    @Override
    public void handleDeleteUser(Long id) throws IdInvalidException {
        User user = this.userRepository.findById(id).orElseThrow(
                () -> new IdInvalidException("user is not exist"));
        if (user != null) {
            this.userRepository.delete(user);
        }
    }

    @Override
    public UserDTO handleUpdateUser(User user) throws IdInvalidException {
        User currentUser = this.userRepository.findById(user.getId()).orElseThrow(() -> new IdInvalidException("id is not found: " + user.getId()));
        if (currentUser != null) {
            if (user.getUsername() != null) {
                currentUser.setUsername(user.getUsername());
            }
            if (user.getAddress() != null) {
                currentUser.setAddress(user.getAddress());
            }
            if (user.getRole() != null) {
                currentUser.setRole(user.getRole());
            }
            if(user.getAge() != 0){
                currentUser.setAge(user.getAge());
            }
            currentUser = this.userRepository.save(currentUser);
        }
        System.out.println(user.getAge());
        System.out.println(currentUser.getAge());
        return currentUser.toDTO(UserDTO.class);
    }

    @Override
    public ResultPaginationDTO handleGetAllUsers(Specification spec, Pageable pageable) {
        Page<User> users = this.userRepository.findAll(spec, pageable);
        ResultPaginationDTO result = new ResultPaginationDTO();
        MetaDTO metaDTO = new MetaDTO();
        metaDTO.setPage(pageable.getPageNumber() + 1);
        metaDTO.setPageSize(pageable.getPageSize() + 1);
        metaDTO.setTotal(users.getTotalElements());
        metaDTO.setTotalPages(users.getTotalPages());
        result.setMetaDTO(metaDTO);
        result.setResult(users.getContent());
        return result;
    }

    @Override
    public String getUsernameById(Long id) throws IdInvalidException {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new IdInvalidException("user not found"))
                .getUsername();
    }

}
