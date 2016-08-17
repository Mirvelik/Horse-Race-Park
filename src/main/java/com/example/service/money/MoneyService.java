package com.example.service.money;

import com.example.entity.Bet;
import com.example.entity.Horse;

public interface MoneyService {
    void restock();

    void pay(Horse horse);

    void pay(Bet bet);
}
