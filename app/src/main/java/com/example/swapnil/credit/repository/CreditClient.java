package com.example.swapnil.credit.repository;

import android.os.AsyncTask;

import com.example.swapnil.credit.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class CreditClient extends AsyncTask<Message, Void, Boolean> {

    @Override
    protected Boolean doInBackground(Message... messages) {
        for (Message message : messages) {
            syncMessage(message);
        }
        return true;
    }

    boolean syncMessage(Message message) {
        HttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost("http://35.239.245.108/credit");
        request.addHeader("content-type", "application/json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            request.setEntity(new StringEntity(mapper.writeValueAsString(message)));
            HttpResponse response = client.execute(request);
            System.out.println("Response " + response.toString() + " Status " + response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == 200) {
                return true;
            }
            System.out.println("Request failed for " + message.getContent());
        } catch (Exception e) {
            throw new IllegalStateException("Fail to parse", e);
        }
        return false;
    }
}
