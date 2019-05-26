package com.example.swapnil.credit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.swapnil.credit.model.CreditInfo;
import com.example.swapnil.credit.model.Message;
import com.example.swapnil.credit.service.PermissionService;
import com.example.swapnil.credit.service.SmsService;
import com.example.swapnil.credit.service.spend.CreditCardParser;

import java.util.LinkedList;
import java.util.List;

public class SmsSyncActivity extends AppCompatActivity {
    private PermissionService permissionService;
    private SmsService smsService;
    private CreditCardParser cardParser;
    private static final String TAG = "SmsSyncActivity";

    public SmsSyncActivity() {
        this.smsService = new SmsService(this);
        this.permissionService = new PermissionService(this);
        this.cardParser = new CreditCardParser();
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
                    loadInDbMessages();
                } else {
                    permissionService.requestSmsPermission();
                }
            }
        });
    }

    void loadInDbMessages() {
        List<Message> messages = smsService.readMessages();
        List<CreditInfo> creditInfos = new LinkedList<>();
        for (Message message : messages) {
            String messageBody = message.getBody();
            if (cardParser.hasCreditInfo(messageBody)) {
                creditInfos.add(cardParser.parse(messageBody));
            }
        }
        Log.v(TAG,creditInfos.get(0).toString());
    }
}