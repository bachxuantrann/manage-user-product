package com.gem_training.manage_user_and_product.entity;

import com.gem_training.manage_user_and_product.util.SecurityUtil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity<DTO> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected Instant createdAt;
    protected Instant updatedAt;
    protected String createdBy;
    protected String updatedBy;

    @PrePersist
    public void handleBeforeCreate() {
        this.createdAt = Instant.now();
        this.createdBy = SecurityUtil.getCurrentUserLogin();
    }

    @PreUpdate
    public void handleBeforeUpdate() {
        this.updatedBy = SecurityUtil.getCurrentUserLogin();
        this.updatedAt = Instant.now();
    }

    //    convert entity to dto
    public DTO toDTO(Class<DTO> clazz) {
        try {
//            new instance of dto
            DTO dto = clazz.getDeclaredConstructor().newInstance();
//            get array fields of dto
            Field[] dtoFields = clazz.getDeclaredFields();
//            get array fields of entity
            Field[] entityFields = getAllFields(this.getClass());
            for (Field dtoField : dtoFields) {
                dtoField.setAccessible(true);
                for (Field entityField : entityFields) {
                    entityField.setAccessible(true);
                    if (dtoField.getName().equals(entityField.getName()) & dtoField.getType().equals(entityField.getType())) {
                        Object value = entityField.get(this);
                        dtoField.set(dto, value);
                        break;
                    }
                }
            }
            return dto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static Field[] getAllFields(Class<?> type) {
        List<Field> fields = new ArrayList<>();
        while (type != null && type != Object.class) {
            fields.addAll(Arrays.asList(type.getDeclaredFields()));
            type = type.getSuperclass();
        }
        return fields.toArray(new Field[0]);
    }
}
