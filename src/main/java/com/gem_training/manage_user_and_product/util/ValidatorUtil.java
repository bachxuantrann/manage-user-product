package com.gem_training.manage_user_and_product.util;

import com.gem_training.manage_user_and_product.exception.ValidatorException;
import com.gem_training.manage_user_and_product.util.annotation.Pattern;
import com.gem_training.manage_user_and_product.util.annotation.Validator;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

public class ValidatorUtil {
    public static <T> boolean validate(T entity) throws IllegalAccessException {
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Validator validator = field.getAnnotation(Validator.class);
            if (validator != null && validator.required()) {
                Object value = field.get(entity);
                if (field.getType().isPrimitive()) continue;
                if (isEmpty(value)) {
                    if (validator.messageCode() != null && !validator.messageCode().isEmpty()) {
                        throw new ValidatorException(validator.messageCode(), Boolean.TRUE);
                    } else {
                        throw new ValidatorException(field.getName() + "is empty", Boolean.FALSE);
                    }
                }
                for (Pattern pattern : validator.patterns()) {
                    if (value instanceof String str) {
                        if (!str.matches(pattern.pattern())) {
                            throw new ValidatorException(pattern.messageCode(), Boolean.TRUE);
                        }
                    }
                }
            }
        }
        return true;
    }

    private static boolean isEmpty(Object value) {
        if (value == null) return true;

        if (value instanceof String) {
            return ((String) value).trim().isEmpty();
        }

        if (value instanceof Collection<?>) {
            return ((Collection<?>) value).isEmpty();
        }

        if (value instanceof Map<?, ?>) {
            return ((Map<?, ?>) value).isEmpty();
        }

        if (value.getClass().isArray()) {
            return Array.getLength(value) == 0;
        }

        return false; // các object khác chỉ null là sai
    }
}
