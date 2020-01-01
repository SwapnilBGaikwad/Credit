package com.example.swapnil.credit.repository;

import com.example.swapnil.credit.model.Message;

import org.junit.Test;

public class CreditClientTest {
    @Test
    public void test() {
        CreditClient client = new CreditClient();
        client.syncMessage(new Message("INR244.00 debited on Credit Card XX8002 on 30-Nov-19.Info:AJFAN DATES AND." +
                "Avbl Lmt:INR1,52,929.00.Call 18002662 for dispute or SMS BLOCK 8002 to 9215676766"));
    }
}