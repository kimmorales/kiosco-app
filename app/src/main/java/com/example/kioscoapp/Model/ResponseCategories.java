package com.example.kioscoapp.Model;

import java.util.ArrayList;
import java.util.List;

public class ResponseCategories {
    private String responseCode;
    private ArrayList<CategoriesMoneyCenter> data;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public ArrayList<CategoriesMoneyCenter> getData() {
        return data;
    }

    public void setData(ArrayList<CategoriesMoneyCenter> data) {
        this.data = data;
    }
}
