package com.example.swapnil.credit.service;

import android.app.Activity;

import com.example.swapnil.credit.model.CreditInfo;
import com.example.swapnil.credit.model.Message;
import com.example.swapnil.credit.repository.CreditDB;
import com.example.swapnil.credit.service.spend.CreditCardParser;

import java.util.List;

public class SMSImportService {
    private SMSService SMSService;
    private CreditCardParser cardParser;
    private CreditDB creditDB;

    public SMSImportService(Activity activity) {
        this.SMSService = new SMSService(activity);
        this.cardParser = new CreditCardParser();
        this.creditDB = new CreditDB(activity);
    }

    public void loadMessagesInDb() {
        List<Message> messages = SMSService.readMessages();
        for (Message message : messages) {
            String messageBody = message.getBody();
            if (cardParser.hasCreditInfo(messageBody)) {
                CreditInfo creditInfo = cardParser.parse(messageBody);
                creditDB.createRecord(creditInfo);
            }
        }
    }
}
