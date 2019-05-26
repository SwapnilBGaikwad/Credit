package com.example.swapnil.credit.service;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;

import com.example.swapnil.credit.model.Message;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SmsService {
    private Activity activity;

    public SmsService(Activity activity) {
        this.activity = activity;
    }

    public List<Message> readMessages() {
        Cursor cursor = activity.getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
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
