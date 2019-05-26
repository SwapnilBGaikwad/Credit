package com.example.swapnil.credit;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.swapnil.credit.repository.CreditDB;
import com.example.swapnil.credit.service.CreditService;

public class HomeActivity extends AppCompatActivity {
    private CreditService creditService;

    public HomeActivity() {
        creditService = new CreditService(new CreditDB(this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Double totalSpend = creditService.getTotalSpend();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Your total spend is : " + totalSpend, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
