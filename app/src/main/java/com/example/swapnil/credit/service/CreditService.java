package com.example.swapnil.credit.service;

import android.content.Context;
import com.example.swapnil.credit.model.CreditInfo;
import com.example.swapnil.credit.model.Message;
import com.example.swapnil.credit.repository.CreditDB;

import java.util.LinkedList;
import java.util.List;

public class CreditService {
    private CreditDB creditDB;
    private DateService dateService;
    private SMSService smsService;

    public CreditService(CreditDB creditDB, Context context) {
        this.creditDB = creditDB;
        this.dateService = new DateService();
        smsService = new SMSService(context);
    }

    public Double getTotalSpend() {
        List<CreditInfo> allCreditInfo = creditDB.getAllCreditInfo();
        Double total = 0D;
        for (CreditInfo creditInfo : allCreditInfo) {
            if (dateService.isCurrentBillCycle(creditInfo.getDate())) {
                total += creditInfo.getAmount();
            }
        }
        return total;
    }

    public List<CreditInfo> getCreditInfo() {
        List<CreditInfo> infoList = new LinkedList<>();
        for (CreditInfo info : creditDB.getAllCreditInfo()) {
            if (dateService.isCurrentBillCycle(info.getDate())) {
                infoList.add(info);
            }
        }
        return infoList;
    }
}
