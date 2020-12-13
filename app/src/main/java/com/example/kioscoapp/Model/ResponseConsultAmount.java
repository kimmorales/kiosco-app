package com.example.kioscoapp.Model;

import java.util.List;

public class ResponseConsultAmount {
    private String responseCode;
    private List<ConsultAmountsMoneyCenter> servicesConsult;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public List<ConsultAmountsMoneyCenter> getServicesConsult() {
        return servicesConsult;
    }

    public void setServicesConsult(List<ConsultAmountsMoneyCenter> servicesConsult) {
        this.servicesConsult = servicesConsult;
    }
}
