package com.example.service.input;

import com.example.common.InputType;
import com.example.dto.ConsoleCommandsDTO;
import com.example.entity.Bet;
import com.example.service.bet.BetService;
import com.example.service.horse.HorseService;
import com.example.service.money.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.common.MessagesOfErrors.INVALID_HORSE_NUMBER;
import static com.example.common.MessagesOfErrors.UNEXPECTED_ERROR;

@Service
public class InputServiceImpl implements InputService {

    private final MoneyService moneyService;
    private final HorseService horseService;
    private final BetService betService;


    @Autowired
    public InputServiceImpl(MoneyService moneyService, HorseService horseService, BetService betService) {
        this.moneyService = moneyService;
        this.horseService = horseService;
        this.betService = betService;
    }

    @Override
    public ConsoleCommandsDTO apply(ConsoleCommandsDTO input) {
        if (input.isError()) {
            return input;
        }

        InputType inputType = input.getType();

        if (InputType.RESTOCK.equals(inputType)) {
            moneyService.restock();
            return input;
        }

        if (InputType.SET_WIN.equals(inputType)) {
            return horseService.setNewWinner(input);
        }

        if (InputType.SET_BET.equals(inputType)) {

            Bet bet = betService.addBet(
                    Integer.valueOf(input.getFirstArgument()),
                    Integer.valueOf(input.getSecondArgument())
            );

            if (bet != null) {
                moneyService.pay(bet);
            } else {
                input.setError(true);
                input.setErrorMsg(INVALID_HORSE_NUMBER);
            }

            return input;
        }

        input.setError(true);
        input.setErrorMsg(UNEXPECTED_ERROR);
        return input;
    }


}
