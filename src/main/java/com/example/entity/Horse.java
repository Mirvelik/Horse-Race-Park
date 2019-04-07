package com.example.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Getter
//@Setter
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

    public Horse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOdds() {
        return odds;
    }

    public void setOdds(Integer odds) {
        this.odds = odds;
    }

    public Boolean getIsWin() {
        return isWin;
    }

    public void setIsWin(Boolean win) {
        isWin = win;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + odds + ", " + ((isWin) ? "won" : "lost");
    }
}
