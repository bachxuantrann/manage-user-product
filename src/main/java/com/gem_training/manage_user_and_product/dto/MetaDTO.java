package com.gem_training.manage_user_and_product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MetaDTO {
    private int page;
    private int pageSize;
    private int totalPages;
    private long total;
}
