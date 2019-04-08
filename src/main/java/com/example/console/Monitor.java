package com.example.console;


import com.example.common.InputType;
import com.example.dto.ConsoleCommandsDTO;
import com.example.service.InputService;
import com.example.service.OutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Monitor implements CommandLineRunner {

    private final InputService inputService;
    private final OutputService outputService;
    private final ConsoleScanner scanner;

    @Autowired
    public Monitor(InputService inputService, OutputService outputService, ConsoleScanner scanner) {
        this.inputService = inputService;
        this.outputService = outputService;
        this.scanner = scanner;
    }

    @Override
    public void run(String... strings) throws Exception {
        outputService.printWelcomeInfo();
        processUserInput();
    }

    private void processUserInput() {
        ConsoleCommandsDTO input = scanner.getNextUserInput();

        while (!InputType.QUIT.equals(input.getType())) {
            outputService.printInfo(inputService.apply(input));
            input = scanner.getNextUserInput();
        }
    }
}
