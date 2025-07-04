package com.gem_training.manage_user_and_product.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Validator {
    boolean required() default false;
    String messageCode() default "";
    boolean allowNull() default false;
    Pattern[] patterns() default {};
}
