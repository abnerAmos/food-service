package com.food.service.dto.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductRequest implements Serializable {

    private String name;
    private String description;
    private BigDecimal price;
    private Boolean active;
    private Long restaurantId;
}
