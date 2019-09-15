package com.example.swapnil.credit;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.example.swapnil.credit.repository.CreditDB;
import com.example.swapnil.credit.service.CreditService;

public class HomeActivity extends AppCompatActivity {
    private CreditService creditService;

    public HomeActivity() {
        creditService = new CreditService(new CreditDB(this), this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView textView = findViewById(R.id.textView);
        textView.setText(getText());
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDetailsActivity();
            }
        });
    }

    private String getText() {
        Resources resources = getResources();
        final double totalSpend = creditService.getTotalSpend();
        return resources.getString(R.string.total_spend, totalSpend);
    }

    private void showDetailsActivity() {
        Intent intent = new Intent(this, ExpenseDetailsActivity.class);
        startActivity(intent);
        finish();
    }

}
