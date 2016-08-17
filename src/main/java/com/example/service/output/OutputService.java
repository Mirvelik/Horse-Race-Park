package com.example.service.output;

import com.example.dto.ConsoleCommandsDTO;
import com.example.dto.PayingDTO;
import com.example.entity.Horse;

public interface OutputService {
    void printBaseInfo();

    void printError(ConsoleCommandsDTO input);

    void printPayingIsSuccess(PayingDTO payingDTO);

    void printPayingIsFail(PayingDTO payingDTO);

    void printNoPayout(Horse horse);
}
