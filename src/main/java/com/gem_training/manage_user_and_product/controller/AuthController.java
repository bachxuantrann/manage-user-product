package com.gem_training.manage_user_and_product.controller;

import com.gem_training.manage_user_and_product.dto.LoginDTO;
import com.gem_training.manage_user_and_product.dto.ResLoginDTO;
import com.gem_training.manage_user_and_product.exception.IdInvalidException;
import com.gem_training.manage_user_and_product.service.CustomeUserDetailsService;
import com.gem_training.manage_user_and_product.util.annotation.ApiMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final CustomeUserDetailsService customeUserDetailsService;

    public AuthController(AuthenticationManager authenticationManager, CustomeUserDetailsService customeUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.customeUserDetailsService = customeUserDetailsService;
    }

    @PostMapping("/login")
    @ApiMessage("User login")
    public ResponseEntity<ResLoginDTO> login(@RequestBody LoginDTO loginDTO) throws IdInvalidException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
        );
        UserDetails userDetails = customeUserDetailsService.loadUserByUsername(loginDTO.getUsername());
        String role = userDetails.getAuthorities().stream().findFirst().get().getAuthority();
        ResLoginDTO resLoginDTO = new ResLoginDTO(loginDTO.getUsername(), role);
        return ResponseEntity.status(HttpStatus.OK).body(resLoginDTO);
    }
}
