package com.example.swapnil.credit;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

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

        TextView textView = findViewById(R.id.textView);
        textView.setText(getText());
        textView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                showHomeActivity();
                return true;
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "This is Home Screen" , Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private String getText() {
        Resources resources = getResources();
        final double totalSpend = creditService.getTotalSpend();
        return resources.getString(R.string.total_spend, totalSpend);
    }

    private void showHomeActivity() {
        Intent intent = new Intent(this, ExpenseDetailsActivity.class);
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

}
