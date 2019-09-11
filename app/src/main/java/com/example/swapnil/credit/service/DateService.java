package com.example.swapnil.credit.service;

import java.util.Date;

class DateService {
    private final static int DAY_OF_MONTH = 17;

    boolean isCurrentBillCycle(Date purchaseDate) {
        return purchaseDate.after(getBillingStart());
    }

    private Date getBillingStart() {
        Date current = new Date();
        if (current.getDay() <= DAY_OF_MONTH) {
            return getDateForMonth(-1);
        } else {
            return getDateForMonth(0);
        }
    }

    private Date getDateForMonth(int monthDiff) {
        Date currentDate = new Date();
        int month = currentDate.getMonth();
        int year = currentDate.getYear();
        return new Date(year, month + monthDiff, DAY_OF_MONTH);
    }
}
