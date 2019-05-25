package com.example.swapnil.credit.service.spend;

import com.example.swapnil.credit.model.CreditInfo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class CreditCardParserTest {
    @Test
    public void testParse() {
        String message = "INR94.00 debited on Credit Card XX4007 on 25-May-19.Info:MAULI PETROLIUM.Avbl Lmt:INR48,856.80.Call 18002662 for dispute or SMS BLOCK 8002 to 8945784387";
        CreditCardParser parser = new CreditCardParser();

        assertSame(true, parser.hasCreditInfo(message));
        CreditInfo creditInfo = parser.parse(message);

        assertEquals(94.00, creditInfo.getAmount(),0);
        assertEquals("XX4007", creditInfo.getCardNumber());
        assertEquals("25-May-19", creditInfo.getDate());
        assertEquals("MAULI PETROLIUM", creditInfo.getReason());
    }
}