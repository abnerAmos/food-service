package com.food.service.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class OrderItemRequest {

    @Min(1)
    @NotBlank
    private Integer quantity;
    @NotBlank
    private BigDecimal unitPrice;
    @NotBlank
    private BigDecimal totalValue;
    private String observation;
    private Long orderId;
    private Long productId;

}
