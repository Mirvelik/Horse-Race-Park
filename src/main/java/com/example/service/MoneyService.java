package com.example.service;

import com.example.entity.Bet;
import com.example.entity.Horse;
import com.example.entity.Money;
import com.example.repository.BetRepository;
import com.example.repository.HorseRepository;
import com.example.repository.MoneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;

@Service
public class MoneyService {

    private final MoneyRepository moneyRepository;
    private final BetRepository betRepository;
    private final OutputService outputService;
    private final BetService betService;
    private final HorseRepository horseRepository;

    @Autowired
    public MoneyService(MoneyRepository moneyRepository, BetRepository betRepository, OutputService outputService, BetService betService, HorseRepository horseRepository) {
        this.moneyRepository = moneyRepository;
        this.betRepository = betRepository;
        this.outputService = outputService;
        this.betService = betService;
        this.horseRepository = horseRepository;
    }

    public void restock() {
        Iterator<Money> iterator = moneyRepository.findAll().iterator();

        while (iterator.hasNext()) {
            Money money = iterator.next();
            money.setQuantity(10);

            moneyRepository.save(money);
        }
    }

    @Transactional
    public void pay(Horse horse) {

        if (horse.getIsWin()) {
            horse.getBets()
                    .stream()
                    .map(betService::payByBet)
                    .forEach(outputService::printPaying);
        } else {
            outputService.printNoPayout(horse);
        }
    }

    @Transactional
    public void pay(Bet bet) {
        Horse horse = horseRepository.getOne(bet.getHorse().getId());
        pay(horse);
        betRepository.delete(bet); //todo: delete this when run in PROD mode
    }
}
