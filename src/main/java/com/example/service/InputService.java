package com.example.service;

import com.example.common.InputType;
import com.example.dto.ConsoleCommandsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.common.MessagesOfErrors.UNEXPECTED_ERROR;

@Service
public class InputService {

    private final MoneyService moneyService;
    private final HorseService horseService;
    private final BetService betService;


    @Autowired
    public InputService(MoneyService moneyService, HorseService horseService, BetService betService) {
        this.moneyService = moneyService;
        this.horseService = horseService;
        this.betService = betService;
    }

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
            return betService.makeBet(input);
        }

        input.setError(true);
        input.setErrorMsg(UNEXPECTED_ERROR);
        return input;
    }
}
