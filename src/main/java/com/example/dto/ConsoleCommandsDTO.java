package com.example.dto;

import com.example.common.InputType;
//import lombok.Getter;
//import lombok.Setter;

//@Getter
//@Setter
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

    public String getFirstArgument() {
        return firstArgument;
    }

    public void setFirstArgument(String firstArgument) {
        this.firstArgument = firstArgument;
    }

    public String getSecondArgument() {
        return secondArgument;
    }

    public void setSecondArgument(String secondArgument) {
        this.secondArgument = secondArgument;
    }

    public InputType getType() {
        return type;
    }

    public void setType(InputType type) {
        this.type = type;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
