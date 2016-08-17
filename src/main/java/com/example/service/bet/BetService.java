package com.example.service.bet;

import com.example.dto.PayingDTO;
import com.example.entity.Bet;

public interface BetService {
    Bet addBet(Integer horseId, Integer value);

    PayingDTO payByBet(Bet bet);
}
