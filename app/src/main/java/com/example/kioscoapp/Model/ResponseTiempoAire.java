package com.example.kioscoapp.Model;

import java.util.ArrayList;

public class ResponseTiempoAire {
    private String responseCode;
    private ArrayList<TiempoAire> data;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public ArrayList<TiempoAire> getData() {
        return data;
    }

    public void setData(ArrayList<TiempoAire> data) {
        this.data = data;
    }
}
