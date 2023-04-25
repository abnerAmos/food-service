package com.food.service.dto.response;

import com.food.service.model.Restaurant;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductResponse implements Serializable {

    private String name;
    private String description;
    private BigDecimal price;
    private Boolean active;
    private String restaurant;
}
