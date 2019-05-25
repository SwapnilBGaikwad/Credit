package com.example.swapnil.credit.model;

public class Message {
    private String body;

    public Message(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return getBody();
    }
}
