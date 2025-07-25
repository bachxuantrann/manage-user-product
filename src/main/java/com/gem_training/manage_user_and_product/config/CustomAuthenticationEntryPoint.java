package com.gem_training.manage_user_and_product.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gem_training.manage_user_and_product.dto.RestReponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    public CustomAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");

        RestReponse<Object> res = new RestReponse<>();
        res.setError("Unauthorized");
        res.setMessage("You need to log in first");
        res.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        res.setData(null);
        String body = objectMapper.writeValueAsString(res);
        response.getWriter().write(body);
    }
}
