package com.gem_training.manage_user_and_product.exception;


import com.gem_training.manage_user_and_product.dto.RestReponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {
            IdInvalidException.class,
            UsernameNotFoundException.class,
            BadCredentialsException.class,
            InternalAuthenticationServiceException.class
    })
    public ResponseEntity<RestReponse<Object>> handleIdInvalidException(Exception ex) {
        RestReponse<Object> res = new RestReponse<Object>();
        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
        res.setError(ex.getMessage());
        res.setMessage("Invalid username or password");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestReponse<Object>> validationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        RestReponse<Object> res = new RestReponse<Object>();
        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
        List<String> errors = fieldErrors.stream().map(f -> f.getDefaultMessage()).collect(Collectors.toUnmodifiableList());
        res.setMessage(errors.size() > 1 ? errors : errors.get(0));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }
    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<RestReponse<Object>> accessDenied(AuthorizationDeniedException ex){
        RestReponse<Object> res = new RestReponse<Object>();
        res.setStatusCode(HttpStatus.FORBIDDEN.value());
        res.setMessage("You do not have permission to access this resource");
        res.setError(ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
    }
}
