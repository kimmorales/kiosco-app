package com.example.kioscoapp.Model;

public class ConsultAmountsMoneyCenter {
    private String responseCode;
    private String countryCode;
    private String formatId;
    private String Tr_Id;
    private String customerName;
    private String amount;
    private String dateExpiration;
    private String mounth;
    private String serviceId;
    private String accountNumber;
    private String statusFac;
    private String currency;
    private boolean allowOpenPayment;
    private String dateCreate;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getFormatId() {
        return formatId;
    }

    public void setFormatId(String formatId) {
        this.formatId = formatId;
    }

    public String getTr_Id() {
        return Tr_Id;
    }

    public void setTr_Id(String tr_Id) {
        Tr_Id = tr_Id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(String dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public String getMounth() {
        return mounth;
    }

    public void setMounth(String mounth) {
        this.mounth = mounth;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getStatusFac() {
        return statusFac;
    }

    public void setStatusFac(String statusFac) {
        this.statusFac = statusFac;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isAllowOpenPayment() {
        return allowOpenPayment;
    }

    public void setAllowOpenPayment(boolean allowOpenPayment) {
        this.allowOpenPayment = allowOpenPayment;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }
}
