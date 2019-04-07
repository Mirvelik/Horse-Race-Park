package com.example.service;

import com.example.dto.ConsoleCommandsDTO;
import com.example.dto.PayingDTO;
import com.example.entity.Horse;
import com.example.repository.HorseRepository;
import com.example.repository.MoneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.example.common.Messages.*;

@Service
public class OutputService {

    private final HorseRepository horseRepository;
    private final MoneyRepository moneyRepository;

    @Autowired
    public OutputService(HorseRepository horseRepository, MoneyRepository moneyRepository) {
        this.horseRepository = horseRepository;
        this.moneyRepository = moneyRepository;
    }

    public void printContent() {
        System.out.println(INVENTORY_LINE);
        moneyRepository.findAll().forEach(System.out::println);

        System.out.println(HORSES_LINE);
        horseRepository.findAll().forEach(System.out::println);
    }

    public void printInfo(ConsoleCommandsDTO input) {

        if (input.isError()) {
            System.out.println(input.getErrorMsg() + input.getFirstArgument());
        }else{
            printContent();
        }

    }

    public void printPaying(PayingDTO payingDTO) {
        if (payingDTO.isSuccess()) {
            System.out.println(PAYOUT + payingDTO.getHorseName() + "," + DOLLAR + payingDTO.getTotalSum());
            System.out.println(DISPENSING_LINE);

            Map<Integer, Integer> bills = payingDTO.getBills();

            for (Map.Entry<Integer, Integer> entry : bills.entrySet()) {
                System.out.println(DOLLAR + entry.getKey() + ": " + entry.getValue());
            }

        } else {
            System.out.println(INSUFFICIENT_FUNDS + DOLLAR + payingDTO.getTotalSum());
        }
    }

    public void printNoPayout(Horse horse) {
        System.out.println(NO_PAYOUT + horse.getName());
    }
}
