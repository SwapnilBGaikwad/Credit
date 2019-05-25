package com.example.swapnil.credit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.swapnil.credit.model.Message;
import com.example.swapnil.credit.service.PermissionService;
import com.example.swapnil.credit.service.SmsService;

import java.util.List;

public class SmsSyncActivity extends AppCompatActivity {
    private PermissionService permissionService;
    private SmsService smsService;

    public SmsSyncActivity() {
        this.smsService = new SmsService(this);
        this.permissionService = new PermissionService(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_sync);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button getPermissionButton = findViewById(R.id.permission);
        getPermissionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (permissionService.hasSmsPermission()) {
                    readMessages();
                } else {
                    permissionService.requestSmsPermission();
                }
            }
        });
    }

    void readMessages() {
        List<Message> messages = smsService.readMessages();
        for (Message message : messages) {
            Toast toast = Toast.makeText(getApplicationContext(), message.getBody(), Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
