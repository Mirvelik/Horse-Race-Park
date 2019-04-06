package com.example.entity;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static com.example.common.Messages.DOLLAR;

@Entity
@Getter
@Setter
public class Money {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MONEY_SEQ")
    @SequenceGenerator(name = "MONEY_SEQ", sequenceName = "SEQUENCE_MONEY", allocationSize = 1)
    private Integer id;

    @NotNull
    private Integer nominal;

    @NotNull
    private Integer quantity;

    @Override
    public String toString() {
        return DOLLAR + nominal + "," + quantity;
    }
}
