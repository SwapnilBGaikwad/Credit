package com.example.swapnil.credit.service;

import android.content.Context;

import com.example.swapnil.credit.model.Message;
import com.example.swapnil.credit.repository.CreditClient;

import java.util.List;

public class SMSImportService {
    private SMSService SMSService;
    private CreditClient creditClient;

    public SMSImportService(Context context) {
        this.SMSService = new SMSService(context);
        this.creditClient = new CreditClient();
    }

    public void loadMessagesInDb() {
        Message[] messages = SMSService.readMessages().toArray(new Message[0]);
        creditClient.execute(messages);
    }

    public void addMessages(List<String> messages) {
        for (String message : messages) {
            creditClient.execute(new Message(message));
        }
    }
}
