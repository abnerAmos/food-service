package com.food.service.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_restaurant")
public class Restaurant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // transforma o campo em NOT NULL
    private String name;

    private BigDecimal deliveryFee;

    @NotNull
    private Boolean active;

    @NotNull
    private Boolean openUp;

    @NotNull
    private LocalDateTime  dateRegistrer;

    private LocalDateTime dateUpdate;

    @ManyToOne
    @JoinColumn(name = "kitchen_id", nullable = false)  // "name" opcional, já esta implicito o none do campo + "_id"
    private Kitchen kitchen;

    /* @JoinTable - Cria uma tabela de Associação no DB, onde gera campos que associam tabelas.
     joinColumns - Gera uma coluna aonde faz associação com a tabela atual.
     inverseJoinColumns - Gera uma coluna aonde faz associação com a tabela atribuida.
     */
    @ManyToMany
    @NotNull
    @JoinTable(name = "restaurant_type_payment",
                joinColumns = @JoinColumn(name = "restaurant_id"),
                inverseJoinColumns = @JoinColumn(name = "type_payment_id"))
    private List<TypePayment> typePayments;

    public Restaurant() {

    }

    public Restaurant(String name, BigDecimal deliveryFee, Boolean active, Boolean open, LocalDateTime dateRegistrer, LocalDateTime dateUpdate) {
        this.name = name;
        this.deliveryFee = deliveryFee;
        this.active = active;
        this.openUp = open;
        this.dateRegistrer = dateRegistrer;
        this.dateUpdate = dateUpdate;
    }

    public List<TypePayment> getTypePayments() {
        return typePayments;
    }

    public void setTypePayments(List<TypePayment> typePayments) {
        this.typePayments = typePayments;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getOpenUp() {
        return openUp;
    }

    public void setOpenUp(Boolean open) {
        this.openUp = open;
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
