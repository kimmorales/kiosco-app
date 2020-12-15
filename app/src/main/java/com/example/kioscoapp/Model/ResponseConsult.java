package com.example.kioscoapp.Model;

import java.util.ArrayList;

public class ResponseConsult {
    private String responseCode;
    private ArrayList<Consult> data;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public ArrayList<Consult> getData() {
        return data;
    }

    public void setData(ArrayList<Consult> data) {
        this.data = data;
    }
}
