package com.example.swapnil.credit;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.swapnil.credit.service.SmsListener;
import com.example.swapnil.credit.service.SmsReceiver;

public class MainActivity extends AppCompatActivity implements SmsListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        String message = checkAndRequestPermissions() ? "Got the permission" : "Sorry";
        show(message);
        SmsReceiver smsReceiver = new SmsReceiver();
        smsReceiver.addListener(this);
        readSms();
    }

    private void readSms() {
        Cursor cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        String str = "";
        if (cursor.moveToFirst()) { // must check the result to prevent exception
            for(int i = 0; i < 5; i++) {
                str += cursor.getString(12);
                cursor.moveToNext();
            }
        }
        show(str);
    }

    private void show(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.show();
    }

    private boolean checkAndRequestPermissions() {
        int sms = getPermission();

        if (sms == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, 0);
        return getPermission() == PackageManager.PERMISSION_GRANTED;
    }

    private int getPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);
    }

    @Override
    public void messageReceived(String messageText) {
        show(messageText);
    }
}
