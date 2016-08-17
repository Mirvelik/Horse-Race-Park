package com.example.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Horse {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HORSE_SEQ")
    @SequenceGenerator(name = "HORSE_SEQ", sequenceName = "SEQUENCE_HORSE", allocationSize = 1)
    @Column(name = "id_horse")
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private Integer odds;

    @Column(name = "is_win")
    @NotNull
    private Boolean isWin;

    @OneToMany(mappedBy = "horse"/*, orphanRemoval = true*/)
    private List<Bet> bets = new ArrayList<>();

    @Override
    public String toString() {
        return id + "," + name + "," + odds + ", " + ((isWin) ? "won" : "lost");
    }
}
