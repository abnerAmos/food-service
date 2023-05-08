package com.food.service.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemRequest {

    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalValue;
    private String observation;
    private Long orderId;
    private Long productId;

}
