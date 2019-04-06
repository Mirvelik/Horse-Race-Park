package com.example.service;

import com.example.dto.ConsoleCommandsDTO;
import com.example.entity.Horse;
import com.example.repository.HorseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.common.MessagesOfErrors.INVALID_HORSE_NUMBER;
import static org.hibernate.Hibernate.initialize;

@Service
@Transactional
public class HorseService {

    private final HorseRepository horseRepository;
    private final MoneyService moneyService;

    @Autowired
    public HorseService(HorseRepository horseRepository, MoneyService moneyService) {
        this.horseRepository = horseRepository;
        this.moneyService = moneyService;
    }

    @Transactional
    public ConsoleCommandsDTO setNewWinner(ConsoleCommandsDTO input) {

        try {
            Horse horse = setWinHorse(Integer.valueOf(input.getFirstArgument()));
            moneyService.pay(horse);

        } catch (IllegalArgumentException e) {
            input.setError(true);
            input.setErrorMsg(e.getMessage());
        }

        return input;
    }


    private Horse setWinHorse(Integer horseId) {
        Horse winner = horseRepository.findOne(horseId);

        if (winner == null) { //horse not found
            throw new IllegalArgumentException(INVALID_HORSE_NUMBER);
        }

        Horse previousWinner = horseRepository.findByIsWin(true);
        previousWinner.setIsWin(false);
        horseRepository.save(previousWinner);

        winner.setIsWin(true);
        horseRepository.save(winner);

        initialize(winner.getBets());
        return winner;
    }
}
