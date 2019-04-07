package com.example.dto;

import java.util.Map;

//@Getter
//@Setter
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

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getHorseName() {
        return horseName;
    }

    public void setHorseName(String horseName) {
        this.horseName = horseName;
    }

    public int getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(int totalSum) {
        this.totalSum = totalSum;
    }

    public Map<Integer, Integer> getBills() {
        return bills;
    }

    public void setBills(Map<Integer, Integer> bills) {
        this.bills = bills;
    }
}
