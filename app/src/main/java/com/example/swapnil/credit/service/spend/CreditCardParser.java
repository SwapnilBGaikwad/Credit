package com.example.swapnil.credit.service.spend;

import com.example.swapnil.credit.model.CreditInfo;

public class CreditCardParser {
    private final static String creditContent = "debited on Credit Card";

    public boolean hasCreditInfo(String message) {
        return message.contains(creditContent);
    }

    public CreditInfo parse(String message) {
        String[] split = message.split(" ");
        Double amount = getAmount(split[0]);
        String cardNumber = getCardNumber(split[5]);
        String date = getDate(split[7]);
        String reason = getReason(message);
        return new CreditInfo.CreditBuilder()
                .amount(amount)
                .cardNumber(cardNumber)
                .date(date)
                .reason(reason)
                .build();
    }

    private String getReason(String reasonInfo) {
        return reasonInfo.split(":")[1].split("\\.")[0];
    }

    private String getDate(String dateInfo) {
        return dateInfo.split("\\.")[0];
    }

    private Double getAmount(String amountInfo) {
        return Double.parseDouble(amountInfo.substring(3));
    }

    private String getCardNumber(String message) {
        return message;
    }
}