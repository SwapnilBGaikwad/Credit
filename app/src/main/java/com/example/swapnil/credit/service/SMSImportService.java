package com.example.swapnil.credit.service;

import android.app.Activity;

import com.example.swapnil.credit.model.CreditInfo;
import com.example.swapnil.credit.model.Message;
import com.example.swapnil.credit.repository.CreditDB;
import com.example.swapnil.credit.service.spend.CreditCardParser;

import java.util.List;

public class SMSImportService {
    private SmsService smsService;
    private CreditCardParser cardParser;
    private CreditDB creditDB;

    public SMSImportService(Activity activity) {
        this.smsService = new SmsService(activity);
        this.cardParser = new CreditCardParser();
        this.creditDB = new CreditDB(activity);
    }

    public void loadMessagesInDb() {
        List<Message> messages = smsService.readMessages();
        for (Message message : messages) {
            String messageBody = message.getBody();
            if (cardParser.hasCreditInfo(messageBody)) {
                CreditInfo creditInfo = cardParser.parse(messageBody);
                creditDB.createRecord(creditInfo);
            }
        }
        List<CreditInfo> allCreditInfo = creditDB.getAllCreditInfo();
        System.out.println(allCreditInfo.get(0));
    }
}
