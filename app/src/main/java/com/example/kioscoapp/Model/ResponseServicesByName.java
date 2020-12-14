package com.example.kioscoapp.Model;

import java.util.ArrayList;

public class ResponseServicesByName {
    private String responseCode;
    private ArrayList<ServiceByNameMoneyCenter> data;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public ArrayList<ServiceByNameMoneyCenter> getData() {
        return data;
    }

    public void setData(ArrayList<ServiceByNameMoneyCenter> data) {
        this.data = data;
    }
}
