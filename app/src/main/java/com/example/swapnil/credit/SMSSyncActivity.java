package com.example.swapnil.credit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.swapnil.credit.service.PermissionService;
import com.example.swapnil.credit.service.SMSImportService;

public class SMSSyncActivity extends AppCompatActivity {
    private PermissionService permissionService;
    private SMSImportService smsImportService;

    public SMSSyncActivity() {
        this.permissionService = new PermissionService(this);
        this.smsImportService = new SMSImportService(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_sync);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button permissionButton = findViewById(R.id.permission);
        permissionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                if (permissionService.hasSmsPermission()) {
                    smsImportService.loadMessagesInDb();
                    showHomeActivity();
                } else {
                    permissionService.requestSmsPermission();
                    button.setText(R.string.load_messages);
                }
            }
        });
    }

    private void showHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }
}
