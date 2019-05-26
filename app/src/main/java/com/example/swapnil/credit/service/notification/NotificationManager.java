package com.example.swapnil.credit.service.notification;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Calendar;
import java.util.Date;

public class NotificationManager {
    private Activity activity;
    private Context context;

    public NotificationManager(Activity activity) {
        this.activity = activity;
    }

    public void init() {
        this.context = activity.getApplicationContext();
        if (isFirstRun()) {
            registerNotification();
        }
    }

    private void registerNotification() {
        Intent notifyIntent = new Intent(activity, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast
                (context, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Date current = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(current.getYear(), current.getMonth(), 18);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY * 30,
                pendingIntent);
    }

    private boolean isFirstRun() {
        SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(context);
        boolean isFirstRun = wmbPreference.getBoolean("FIRSTRUN", true);
        if (isFirstRun) {
            SharedPreferences.Editor editor = wmbPreference.edit();
            editor.putBoolean("FIRSTRUN", false);
            editor.apply();
            return true;
        }
        return false;
    }
}
