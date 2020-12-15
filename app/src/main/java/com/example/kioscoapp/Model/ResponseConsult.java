package com.example.kioscoapp.Model;

import java.util.ArrayList;

public class ResponseConsult {
    private String responseCode;
    private ArrayList<Consult> servicesConsult;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public ArrayList<Consult> getServicesConsult() {
        return servicesConsult;
    }

    public void setServicesConsult(ArrayList<Consult> servicesConsult) {
        this.servicesConsult = servicesConsult;
    }
}
