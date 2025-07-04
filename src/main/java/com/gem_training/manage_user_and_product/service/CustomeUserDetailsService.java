package com.gem_training.manage_user_and_product.service;

import com.gem_training.manage_user_and_product.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("userDetailsService")
@Service
public class CustomeUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomeUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.gem_training.manage_user_and_product.entity.User user = this.userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username: " + username)
        );
        // case user này ko bằng null đc, nếu null thì orElseThrow ở bên trên luôn nên chỗ này code thừa
        return User.builder().username(user.getUsername()).password(user.getPassword())
                .roles(user.getRole().name()).build();
    }
}
