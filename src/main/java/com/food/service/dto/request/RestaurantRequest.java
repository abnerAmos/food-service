package com.food.service.dto.request;

import java.math.BigDecimal;
import java.util.List;

public class RestaurantRequest {

    private Long id;
    private String name;
    private BigDecimal deliveryFee;
    private Boolean active;
    private Boolean open;
    private Long kitchenId;
    private List<Long> typePaymentId;
    private String postalCode;
    private String placeNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public List<Long> getTypePaymentId() {
        return typePaymentId;
    }

    public void setTypePaymentId(List<Long> typePaymentId) {
        this.typePaymentId = typePaymentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Long getKitchenId() {
        return kitchenId;
    }

    public void setKitchenId(Long kitchenId) {
        this.kitchenId = kitchenId;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(String placeNumber) {
        this.placeNumber = placeNumber;
    }
}
