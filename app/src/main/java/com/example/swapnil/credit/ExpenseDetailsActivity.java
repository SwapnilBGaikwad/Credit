package com.example.swapnil.credit;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;
import com.example.swapnil.credit.model.CreditInfo;
import com.example.swapnil.credit.repository.CreditDB;
import com.example.swapnil.credit.service.CreditService;

import java.util.List;

public class ExpenseDetailsActivity extends AppCompatActivity {
    private CreditService creditService;

    public ExpenseDetailsActivity() {
        creditService = new CreditService(new CreditDB(this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_details);
        ListView listView = findViewById(R.id.expense_list);
        List<CreditInfo> creditInfo = creditService.getCreditInfo();
        listView.setAdapter(new ExpenseDetailsAdaptor(this, creditInfo));
        TextView totalSpendView = findViewById(R.id.total_expense_id);
        Resources resources = getResources();
        String text = resources.getString(R.string.total_spend_details, creditService.getTotalSpend());
        totalSpendView.setText(text);
    }
}
