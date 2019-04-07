package com.example.console;

import com.example.common.InputType;
import com.example.dto.ConsoleCommandsDTO;
import com.example.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

import static com.example.common.MessagesOfErrors.INVALID_BET;
import static com.example.common.MessagesOfErrors.INVALID_COMMAND;
import static com.example.common.StringUtils.isInteger;

@Component
public class ConsoleScanner {
    private final Scanner scanner;
    private final CommandService command;

    @Autowired
    public ConsoleScanner(CommandService command) {
        this.scanner = new Scanner(System.in);
        this.command = command;
    }

    private static String getWinValue(String beforeSplit) {
        return beforeSplit.substring(2);
    }

    public ConsoleCommandsDTO getNextUserInput() {
        final String sourceValue = scanner.nextLine();
        return Optional.ofNullable(sourceValue)
                .map(input -> mapInputToConsoleCommandDTO(input.toUpperCase(), sourceValue))
                .orElse(new ConsoleCommandsDTO(sourceValue, true, INVALID_COMMAND));

    }

    private ConsoleCommandsDTO mapInputToConsoleCommandDTO(String input, String sourceValue){
        if (command.isQuit(input)) {
            return new ConsoleCommandsDTO(input, InputType.QUIT);
        }

        if (command.isRestock(input)) {
            return new ConsoleCommandsDTO(input, InputType.RESTOCK);
        }

        if (command.isSetWinHorse(input)) {
            return new ConsoleCommandsDTO(getWinValue(input), InputType.SET_WIN);
        }

        if (command.isSetBetOnHorse(input)) {

            String[] split = input.split(" ");
            String horseId = split[0];
            String betSum = split[1];

            if (isInteger(horseId) && isInteger(betSum)) {
                return new ConsoleCommandsDTO(horseId, betSum, InputType.SET_BET);
            } else {
                return new ConsoleCommandsDTO(betSum, true, INVALID_BET);
            }
        }

        return new ConsoleCommandsDTO(sourceValue, true, INVALID_COMMAND);
    }

}
