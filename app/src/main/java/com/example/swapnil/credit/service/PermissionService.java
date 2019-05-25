package com.example.swapnil.credit.service;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class PermissionService {
    private Activity activity;

    public PermissionService(Activity activity) {
        this.activity = activity;
    }

    public void requestSmsPermission() {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_SMS}, 0);
    }

    public boolean hasSmsPermission() {
        int sms = ContextCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.READ_SMS);
        return sms == PackageManager.PERMISSION_GRANTED;
    }
}
