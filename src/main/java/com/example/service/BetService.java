package com.example.service;

import com.example.dto.PayingDTO;
import com.example.entity.Bet;
import com.example.entity.Horse;
import com.example.entity.Money;
import com.example.repository.BetRepository;
import com.example.repository.HorseRepository;
import com.example.repository.MoneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class BetService {

    private final HorseRepository horseRepository;
    private final BetRepository betRepository;
    private final MoneyRepository moneyRepository;

    @Autowired
    public BetService(HorseRepository horseRepository, BetRepository betRepository, MoneyRepository moneyRepository) {
        this.horseRepository = horseRepository;
        this.betRepository = betRepository;
        this.moneyRepository = moneyRepository;
    }

    @Transactional
    public Bet addBet(Integer horseId, Integer value) {
        Horse horse = horseRepository.getOne(horseId);

        if (horse == null) {
            return null;
        }

        Bet bet = new Bet();
        bet.setHorse(horse);
        bet.setValue(value);

        betRepository.save(bet);
        return bet;
    }

    @Transactional
    public PayingDTO payByBet(Bet bet) {
        Integer mustPaySum = bet.getValue() * bet.getHorse().getOdds();

        Iterable<Money> moneys = moneyRepository.findAllByOrderByNominalDesc();

        // [nominal => amount of needed banknotes]
        Map<Integer, Integer> bills = calculateHowMuchBillsWillNeed(moneys, mustPaySum);

        PayingDTO payingDTO = new PayingDTO(mustPaySum, bills, bet.getHorse().getName());

        try {
            tryToPay(mustPaySum, moneys, bills);
            betRepository.delete(bet); //because of a bet was paid
        } catch (IllegalStateException e) {
            payingDTO.setSuccess(false);
        }

        return payingDTO;
    }

    private void tryToPay(Integer mustPaySum, Iterable<Money> moneys, Map<Integer, Integer> bills) {

        //checking
        int totalSumInBank = 0;
        for (Map.Entry<Integer, Integer> entry : bills.entrySet()) {
            //total = total + (nominal * quantity)
            totalSumInBank = totalSumInBank + (entry.getKey() * entry.getValue());
        }

        if (totalSumInBank < mustPaySum) {
            throw new IllegalStateException("cannot pay");
        }

        //paying
        for (Money money : moneys) {
            Integer nominal = money.getNominal();
            Integer quantity = money.getQuantity();

            Integer amountOfNeededBills = bills.get(nominal);
            money.setQuantity(quantity - amountOfNeededBills);

            moneyRepository.save(money);
        }
    }

    private Map<Integer, Integer> calculateHowMuchBillsWillNeed(Iterable<Money> moneys, Integer mustPaySum) {
        Map<Integer, Integer> bills = new LinkedHashMap<>();

        for (Money money : moneys) {
            Integer nominal = money.getNominal();
            Integer quantity = money.getQuantity();
            if (quantity == 0) {
                continue;
            }

            int div = mustPaySum / nominal;

            if (div <= quantity) {
                bills.put(nominal, div);
                mustPaySum = mustPaySum % nominal;
            } else {
                bills.put(nominal, quantity);
                mustPaySum = mustPaySum - (nominal * quantity);
            }
        }

        return bills;
    }
}
