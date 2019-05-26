package com.example.swapnil.credit.model;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class CreditInfoTest {
    @Test
    public void builderForCreditInfo() {
        Date date = new Date();
        CreditInfo creditInfo = new CreditInfo.CreditBuilder()
                .cardNumber("xxxxx10")
                .amount(100.0)
                .date(date)
                .reason("SWIGGY")
                .build();

        assertEquals("xxxxx10", creditInfo.getCardNumber());
        assertEquals(date, creditInfo.getDate());
        assertEquals(100.0, creditInfo.getAmount(),0);
        assertEquals("SWIGGY", creditInfo.getReason());
        assertSame(false, creditInfo.isCurrentMonth());
    }
}