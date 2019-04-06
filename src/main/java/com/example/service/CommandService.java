package com.example.service;

import org.springframework.stereotype.Service;

import static com.example.common.StringUtils.isInteger;
import static com.example.common.StringUtils.isNumeric;

@Service
public class CommandService {

    public boolean isRestock(String userInput) {
        return "R".equals(userInput);
    }

    public boolean isSetWinHorse(String userInput) {
        String[] split = userInput.split(" ");
        if (split.length == 2 && "W".equals(split[0]) && isInteger(split[1])) {
            return true;
        }

        return false;
    }

    public boolean isSetBetOnHorse(String userInput) {
        String[] split = userInput.split(" ");

        if (split.length == 2 && isNumeric(split[0]) && isNumeric(split[1])) {
            return true;
        }

        return false;
    }

    public boolean isQuit(String userInput) {
        return "Q".equals(userInput);
    }
}
