package com.example.swapnil.credit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreditInfoTest {
    @Test
    public void builderForCreditInfo() {
        CreditInfo creditInfo = new CreditInfo.CreditBuilder()
                .cardNumber("xxxxx10")
                .amount(100.0)
                .date("10-May-2019")
                .reason("SWIGGY")
                .build();

        assertEquals("xxxxx10", creditInfo.getCardNumber());
        assertEquals("10-May-2019", creditInfo.getDate());
        assertEquals(100.0, creditInfo.getAmount(),0);
        assertEquals("SWIGGY", creditInfo.getReason());
    }
}