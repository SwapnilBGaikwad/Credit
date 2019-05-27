package com.example.swapnil.credit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.swapnil.credit.service.PermissionService;
import com.example.swapnil.credit.service.notification.NotificationManager;

public class MainActivity extends AppCompatActivity {
    private PermissionService permissionService;
    private NotificationManager notificationManager;

    public MainActivity() {
        this.notificationManager = new NotificationManager(this);
        this.permissionService = new PermissionService(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (permissionService.hasSmsPermission()) {
            showHomeActivity();
        } else {
            showSmsSyncActivity();
        }
        notificationManager.init();
    }

    private void showSmsSyncActivity() {
        Intent intent = new Intent(this, SMSSyncActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    private void showHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }
}