package com.example.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static com.example.common.Messages.DOLLAR;

@Entity
public class Money {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MONEY_SEQ")
    @SequenceGenerator(name = "MONEY_SEQ", sequenceName = "SEQUENCE_MONEY", allocationSize = 1)
    private Integer id;

    @NotNull
    private Integer nominal;

    @NotNull
    private Integer quantity;

    public Money() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNominal() {
        return nominal;
    }

    public void setNominal(Integer nominal) {
        this.nominal = nominal;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return DOLLAR + nominal + "," + quantity;
    }
}
