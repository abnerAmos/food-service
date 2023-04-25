package com.food.service.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class RestaurantResponse implements Serializable {

    private String name;
    private BigDecimal deliveryFee;
    private boolean active;
    private boolean openUp;
    private LocalDateTime dateRegistrer;
    private LocalDateTime dateUpdate;


}
