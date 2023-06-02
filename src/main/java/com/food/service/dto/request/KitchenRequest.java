package com.food.service.dto.request;

import javax.validation.constraints.NotBlank;

public class KitchenRequest {

    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
