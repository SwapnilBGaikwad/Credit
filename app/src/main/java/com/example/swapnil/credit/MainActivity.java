package com.example.swapnil.credit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.swapnil.credit.service.PermissionService;

public class MainActivity extends AppCompatActivity {
    private PermissionService permissionService;

    public MainActivity() {
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