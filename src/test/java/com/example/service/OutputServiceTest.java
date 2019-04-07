package com.example.service;

import com.example.repository.HorseRepository;
import com.example.repository.MoneyRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class OutputServiceTest {
    @Mock
    private HorseRepository horseRepository;
    @Mock
    private MoneyRepository moneyRepository;
    private OutputService outputService = new OutputService(horseRepository, moneyRepository);

    @Test
    public void printContentTest() {
    }

    @Test
    public void printInfoTest() {
    }

    @Test
    public void printPayingTest() {
    }

//    @Test
//    public void printNoPayoutTest() {
//        Assert.assertEquals(outputService.);
//    }
}