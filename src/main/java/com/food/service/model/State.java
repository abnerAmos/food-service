package com.food.service.model;

import java.io.Serializable;

public class State implements Serializable {

    private Long id;
    private String name;

    public State() {
    }

    public State(String name, Long id) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
