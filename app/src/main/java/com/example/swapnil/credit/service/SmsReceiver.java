package com.example.swapnil.credit.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import java.util.LinkedList;
import java.util.List;

public class SmsReceiver extends BroadcastReceiver {
    private List<SmsListener> listeners = new LinkedList<>();

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data = intent.getExtras();
        Object[] pdus = (Object[]) data.get("pdus");
        for (int i = 0; i < pdus.length; i++) {
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
            final String messageBody = smsMessage.getMessageBody();
            System.out.println("********************************************************");
            System.out.println("messageBody : " + messageBody);
            for (SmsListener listener : listeners) {
                listener.messageReceived(messageBody);
            }
        }
    }

    public void addListener(SmsListener smsListener) {
        listeners.add(smsListener);
    }
}
