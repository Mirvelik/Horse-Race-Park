package com.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BET_SEQ")
    @SequenceGenerator(name = "BET_SEQ", sequenceName = "SEQUENCE_BET", allocationSize = 1)
    @Column(name = "id_bet")
    private Integer id;

    @NotNull
    private Integer value;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_horse")
    private Horse horse;
}
