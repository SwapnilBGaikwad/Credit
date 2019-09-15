package com.example.swapnil.credit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {
    private static final String TAG = SmsReceiver.class.getSimpleName();
    public static final String pdu_type = "pdus";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "ON RECEIVE BROADCAST", Toast.LENGTH_LONG).show();
        Log.d("ON ","RECEIVE");
        Bundle bundle = intent.getExtras();
        StringBuilder strMessage = new StringBuilder();
        String format = bundle.getString("format");
        Object[] pdus = (Object[]) bundle.get(pdu_type);
        SmsMessage[] msgs = new SmsMessage[pdus.length];
        for (int i = 0; i < msgs.length; i++) {
            msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
            strMessage.append("SMS from ").append(msgs[i].getOriginatingAddress());
            strMessage.append(" :").append(msgs[i].getMessageBody()).append("\n");
        }
        Log.d(TAG, "onReceive: " + strMessage);
        Toast.makeText(context, strMessage, Toast.LENGTH_LONG).show();
    }
}
