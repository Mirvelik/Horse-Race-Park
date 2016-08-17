package com.example.service.command;

public interface CommandService {
    boolean isRestock(String userInput);
    boolean isSetWinHorse(String userInput);
    boolean isSetBetOnHorse(String userInput);
    boolean isQuit(String userInput);
}
