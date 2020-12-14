package com.example.kioscoapp.Model;

import java.util.ArrayList;

public class ResponseCountry {
    private String responseCode;
    private ArrayList<Country> data;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public ArrayList<Country> getData() {
        return data;
    }

    public void setData(ArrayList<Country> data) {
        this.data = data;
    }
}
