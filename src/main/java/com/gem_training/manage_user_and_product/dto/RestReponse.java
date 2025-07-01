package com.gem_training.manage_user_and_product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestReponse<T> {
    private int statusCode;
    private Object error;
    private Object message;
    private T data;
}
