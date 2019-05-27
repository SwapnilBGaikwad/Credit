package com.example.swapnil.credit.model;

import java.util.Date;

public class CreditInfo {
    private String cardNumber;
    private Double amount;
    private Date date;
    private String reason;

    public String getCardNumber() {
        return cardNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "CreditInfo{" +
                "cardNumber='" + cardNumber + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }

    public static class CreditBuilder {
        private CreditInfo creditInfo;

        public CreditBuilder() {
            creditInfo = new CreditInfo();
        }

        public CreditBuilder cardNumber(String cardNumber) {
            creditInfo.cardNumber = cardNumber;
            return this;
        }

        public CreditBuilder amount(Double amount) {
            creditInfo.amount = amount;
            return this;
        }

        public CreditBuilder date(Date date) {
            creditInfo.date = date;
            return this;
        }

        public CreditBuilder reason(String reason) {
            creditInfo.reason = reason;
            return this;
        }

        public CreditInfo build() {
            if (creditInfo.amount == null || creditInfo.cardNumber == null ||
                    creditInfo.date == null || creditInfo.reason == null) {
                throw new IllegalStateException("Missing arguments for credit info");
            }
            return creditInfo;
        }
    }
}
