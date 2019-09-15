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
            messages.add(new Message(cursor.getString(12)));
        }
        cursor.close();
        return messages;
    }
}
