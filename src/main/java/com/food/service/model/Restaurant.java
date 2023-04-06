package com.food.service.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_restaurant")
public class Restaurant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // transforma o campo em NOT NULL
    private String name;

    @NotBlank
    private BigDecimal deliveryFee;

    @NotBlank
    private Boolean active;

    @NotBlank
    private Boolean open;

    @NotBlank
    private LocalDateTime  dateRegistrer;

    private LocalDateTime dateUpdate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "kitchen_id")
    private Kitchen kitchen;

    public Restaurant() {

    }

    public Restaurant(String name, BigDecimal deliveryFee, Boolean active, Boolean open, LocalDateTime dateRegistrer, LocalDateTime dateUpdate) {
        this.name = name;
        this.deliveryFee = deliveryFee;
        this.active = active;
        this.open = open;
        this.dateRegistrer = dateRegistrer;
        this.dateUpdate = dateUpdate;
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

    public LocalDateTime getDateRegistrer() {
        return dateRegistrer;
    }

    public void setDateRegistrer(LocalDateTime dateRegistrer) {
        this.dateRegistrer = dateRegistrer;
    }

    public LocalDateTime getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(LocalDateTime dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Kitchen getKitchen() {
        return kitchen;
    }

    public void setKitchen(Kitchen kitchen) {
        this.kitchen = kitchen;
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

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Restaurant that)) return false;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
