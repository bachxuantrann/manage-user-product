package com.gem_training.manage_user_and_product.service.serviceImpl;

import com.gem_training.manage_user_and_product.dto.UserDTO;
import com.gem_training.manage_user_and_product.entity.User;
import com.gem_training.manage_user_and_product.exception.IdInvalidException;
import com.gem_training.manage_user_and_product.repository.UserRepository;
import com.gem_training.manage_user_and_product.service.UserService;
import com.gem_training.manage_user_and_product.util.enums.RoleEnum;
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
        boolean isExist = this.isUserExist(user.getUsername());
        if(isExist){
            throw new IdInvalidException("user is exist");
        }
        String password = user.getPassword();
        String hassPassword = this.passwordEncoder.encode(password);
        user.setPassword(hassPassword);
        if (user.getRole() == null) {
            user.setRole(RoleEnum.USER);  // gán quyền mặc định
        }
        User newUser = this.userRepository.save(user);
        return newUser.toUserDTO();
    }

    @Override
    public UserDTO handleGetUserDetail(Long id) throws IdInvalidException {
        return this.userRepository.findById(id).orElseThrow(
                () -> new IdInvalidException("user is not exist")
        ).toUserDTO();
    }

    public boolean isUserExist(String userName){
        if (this.userRepository.findByUsername(userName).isPresent()){
            return true;
        }
        return false;
    }
}
