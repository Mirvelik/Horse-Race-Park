package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class PayingDTO {

    private boolean isSuccess;

    private String horseName;
    private int totalSum;

    private Map<Integer, Integer> bills;

    public PayingDTO(int totalSum, Map<Integer, Integer> bills, String horseName) {
        this.totalSum = totalSum;
        this.bills = bills;
        this.horseName = horseName;

        this.isSuccess = true;
    }
}
