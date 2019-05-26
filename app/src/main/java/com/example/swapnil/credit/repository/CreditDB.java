package com.example.swapnil.credit.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.swapnil.credit.model.CreditInfo;

import java.util.LinkedList;
import java.util.List;

public class CreditDB {
    private static final String CREDIT_TABLE = "credit_info";
    private SQLiteDatabase database;

    public CreditDB(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
    }

    public long createRecords(CreditInfo creditInfo) {
        ContentValues values = new ContentValues();
        values.put("amount", creditInfo.getAmount());
        values.put("card_number", creditInfo.getCardNumber());
        values.put("date", creditInfo.getDate());
        values.put("reason", creditInfo.getReason());
        return database.insert(CREDIT_TABLE, null, values);
    }

    public List<CreditInfo> getAllCreditInfo() {
        String[] cols = new String[]{"card_number", "amount", "date", "reason"};
        Cursor mCursor = database.query(true, CREDIT_TABLE, cols, null
                , null, null, null, null, null);
        LinkedList<CreditInfo> creditInfoList = new LinkedList<>();
        if (mCursor == null || !mCursor.moveToNext()) {
            return creditInfoList;
        }
        mCursor.moveToFirst();
        while (mCursor.moveToNext()) {
            String cardNumber = mCursor.getString(0);
            Double amount = mCursor.getDouble(1);
            String date = mCursor.getString(2);
            String reason = mCursor.getString(3);
            CreditInfo creditInfo = new CreditInfo.CreditBuilder()
                    .cardNumber(cardNumber)
                    .amount(amount)
                    .date(date)
                    .reason(reason)
                    .build();
            creditInfoList.add(creditInfo);
        }
        mCursor.close();
        return creditInfoList;
    }
}