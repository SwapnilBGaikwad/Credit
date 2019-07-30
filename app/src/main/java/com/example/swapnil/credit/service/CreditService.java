package com.example.swapnil.credit.service;

import com.example.swapnil.credit.model.CreditInfo;
import com.example.swapnil.credit.repository.CreditDB;

import java.util.List;

public class CreditService {
    private CreditDB creditDB;
    private DateService dateService;

    public CreditService(CreditDB creditDB) {
        this.creditDB = creditDB;
        this.dateService = new DateService();
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
        return creditDB.getAllCreditInfo();
    }
}
