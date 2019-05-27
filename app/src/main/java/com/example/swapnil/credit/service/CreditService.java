package com.example.swapnil.credit.service;

import com.example.swapnil.credit.model.CreditInfo;
import com.example.swapnil.credit.repository.CreditDB;

import java.util.Date;
import java.util.List;

public class CreditService {
    private CreditDB creditDB;

    public CreditService(CreditDB creditDB) {
        this.creditDB = creditDB;
    }

    public Double getTotalSpend() {
        List<CreditInfo> allCreditInfo = creditDB.getAllCreditInfo();
        Double total = 0D;
        for (CreditInfo creditInfo : allCreditInfo) {
            if (creditInfo.getDate().after(getBillingStart())) {
                total += creditInfo.getAmount();
            }
        }
        return total;
    }

    private Date getBillingStart() {
        Date currentDate = new Date();
        return new Date(currentDate.getYear(), currentDate.getMonth(),17);
    }
}
