package com.food.service.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductRequest implements Serializable {

    @NotBlank
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean active;
    private Long restaurantId;
}
