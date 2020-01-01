package com.example.swapnil.credit.service;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.example.swapnil.credit.model.Message;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class SMSService {
    private Context context;

    SMSService(Context context) {
        this.context = context;
    }

    List<Message> readMessages() {
        Cursor cursor = context.getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        if (cursor == null || !cursor.moveToFirst()) {
            return Collections.emptyList();
        }
        List<Message> messages = new LinkedList<>();
        while (cursor.moveToNext()) {
            String text = cursor.getString(12);
            if (text.contains("XX8002")
                    && cursor.getString(2).contains("ICICIB")
                    && !text.contains("OTP")) {
                messages.add(new Message(text));
            }
        }
        cursor.close();
        return messages;
    }
}
