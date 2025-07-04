package com.gem_training.manage_user_and_product.util.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface Pattern {
    String pattern();
    String messageCode();
}
