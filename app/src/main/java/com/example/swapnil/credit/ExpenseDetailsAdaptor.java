package com.example.swapnil.credit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.swapnil.credit.model.CreditInfo;

import java.util.List;

public class ExpenseDetailsAdaptor extends BaseAdapter {
    private Context mContext;
    private List<CreditInfo> creditInfos;

    ExpenseDetailsAdaptor(Context mContext, List<CreditInfo> creditInfos) {
        this.mContext = mContext;
        this.creditInfos = creditInfos;
    }

    @Override
    public int getCount() {
        return creditInfos.size();
    }

    @Override
    public CreditInfo getItem(int index) {
        return creditInfos.get(index);
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.list_element, parent, false);
        }

        CreditInfo creditInfo = getItem(position);
        if (creditInfo == null) {
            return convertView;
        }
        setDataToView(convertView,creditInfo);
        return convertView;
    }

    private void setDataToView(View convertView, CreditInfo creditInfo) {
        TextView spendReason;
        TextView amount;

        spendReason = convertView.findViewById(R.id.spend_on_id);
        spendReason.setText(creditInfo.getReason());
        amount = convertView.findViewById(R.id.amount_id);
        amount.setText(creditInfo.getAmount().toString());
    }

}
