package com.example.swapnil.credit.service;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class DateServiceTest {
    @Test
    public void shouldReturnTrueIfCurrentDateIsLessThanDAY_OF_MONTH() {
        DateService dateService = new DateService();
        Date date = new Date(2019, 7, 24);
        boolean currentBillCycle = dateService.isCurrentBillCycle(date);
        Assert.assertEquals(true, currentBillCycle);
    }
}