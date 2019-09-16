package com.example.swapnil.credit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;
import com.example.swapnil.credit.model.CreditInfo;
import com.example.swapnil.credit.service.SMSImportService;
import com.example.swapnil.credit.service.spend.CreditCardParser;

import java.util.LinkedList;
import java.util.List;

public class SmsReceiver extends BroadcastReceiver {
    public static final String pdu_type = "pdus";

    @Override
    public void onReceive(Context context, Intent intent) {
        SMSImportService smsImportService = new SMSImportService(context);
        Bundle bundle = intent.getExtras();
        String format = bundle.getString("format");
        Object[] pdus = (Object[]) bundle.get(pdu_type);
        List<String> msgs = new LinkedList<>();
        for (Object obj : pdus) {
            msgs.add(SmsMessage.createFromPdu((byte[]) obj, format).getMessageBody());
        }
        smsImportService.addMessages(msgs);
    }
}
