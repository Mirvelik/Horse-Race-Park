package com.example.dto;

import com.example.common.InputType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsoleCommandsDTO {
    private String firstArgument;
    private String secondArgument;

    private InputType type;

    private boolean isError;
    private String errorMsg;

    public ConsoleCommandsDTO(String firstArgument, InputType type) {
        this.firstArgument = firstArgument;
        this.type = type;
    }

    public ConsoleCommandsDTO(String firstArgument, String secondArgumen, InputType type) {
        this.firstArgument = firstArgument;
        this.secondArgument = secondArgumen;
        this.type = type;
    }


    public ConsoleCommandsDTO(String input, boolean isError, String errorMsg) {
        this.firstArgument = input;
        this.isError = isError;
        this.errorMsg = errorMsg;
    }
}
