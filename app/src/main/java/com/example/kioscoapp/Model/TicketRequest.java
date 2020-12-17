package com.example.kioscoapp.Model;

public class TicketRequest {
    private String allowOpenPayment;
    private String amounts;
    private String Ids;
    private String serviceType;

    public TicketRequest() {
        this.allowOpenPayment = "";
        this.amounts = "";
        Ids = "";
        this.serviceType = "";
    }

    public String getAllowOpenPayment() {
        return allowOpenPayment;
    }

    public void setAllowOpenPayment(String allowOpenPayment) {
        this.allowOpenPayment = allowOpenPayment;
    }

    public String getAmounts() {
        return amounts;
    }

    public void setAmounts(String amounts) {
        this.amounts = amounts;
    }

    public String getIds() {
        return Ids;
    }

    public void setIds(String ids) {
        Ids = ids;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
}
