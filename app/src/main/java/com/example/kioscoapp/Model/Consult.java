package com.example.kioscoapp.Model;

public class Consult {
  /*  {
        "countryCode": "CRC",
            "formatId": 1,
            "Tr_Id": "1968",
            "customerName": "Rafael Castro Arguedas",
            "amount": 3500,
            "dateExpiration": "2020-01-08T00:00:00.000Z",
            "dateCreate": "2020-12-14T15:48:09.377Z"
    },*/
    private String countryCode;
    private String formatId;
    private String Tr_Id;
    private String customerName;
    private String amount;
    private String dateExpiration;
    private String dateCreate;
    private boolean isAdded;

    public boolean isAdded() {
        return isAdded;
    }

    public void setAdded(boolean added) {
        isAdded = added;
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

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }
}
