package com.example.kioscoapp.Model;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

public class GeneralResponse<T> {
    private String responseCode;
    private ArrayList<T> data;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public ArrayList<T> getData() {
        return data;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }
}
