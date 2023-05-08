package com.food.service.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderItemResponse implements Serializable {

        private Integer quantity;
        private BigDecimal unitPrice;
        private BigDecimal totalValue;
        private String observation;

}
