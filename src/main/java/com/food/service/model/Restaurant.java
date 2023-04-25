package com.food.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    @NotNull
    private BigDecimal deliveryFee;

    @NotNull
    private Boolean active;

    @NotNull
    private Boolean openUp;

    @NotNull
    private LocalDateTime  dateRegistrer;

    private LocalDateTime dateUpdate;

    @JsonIgnoreProperties("hibernateLazyInitializer")
    @ManyToOne(fetch = FetchType.LAZY) /* Lazy em resumo: Reduz a quantidade de select no DB, gerando um payload menor
                                        carrega o objeto apenas se sua chamada for explicita. */
    @JoinColumn(name = "kitchen_id", nullable = false)  // "name" opcional, já esta implicito o none do campo + "_id"
    private Kitchen kitchen;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Product> products;

    /* @JoinTable - Cria uma tabela de Associação no DB, onde gera campos que associam tabelas.
     joinColumns - Gera uma coluna aonde faz associação com a tabela atual.
     inverseJoinColumns - Gera uma coluna aonde faz associação com a tabela atribuida.
     */
//    @JsonIgnore
    @ManyToMany
    @NotNull
    @JoinTable(name = "restaurant_type_payment",
                joinColumns = @JoinColumn(name = "restaurant_id"),
                inverseJoinColumns = @JoinColumn(name = "type_payment_id"))
    private List<TypePayment> typePayments;

    @Embedded
    @NotNull
    private Address address;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
