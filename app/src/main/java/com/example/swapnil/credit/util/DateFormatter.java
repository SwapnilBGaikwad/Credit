package com.example.swapnil.credit.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatter {
    private static final SimpleDateFormat textFormat = new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH);
    private static final SimpleDateFormat dbFormat = new SimpleDateFormat("dd-MM-yy", Locale.ENGLISH);

    public static Date getDateFromText(String dateString) {
        try {
            return textFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDBTextFromDate(Date date) {
        return dbFormat.format(date);
    }

    public static Date getDateFromDB(String dateString) {
        try {
            return dbFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}