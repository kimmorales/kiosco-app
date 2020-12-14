package com.example.kioscoapp.Model;

public class Country {

    private String Country_Id;
    private String type;
    private long Country_Code;
    private String Currency;
    private String Description;



    public String getCountry_Id() {
        return Country_Id;
    }

    public void setCountry_Id(String country_Id) {
        Country_Id = country_Id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getCountry_Code() {
        return Country_Code;
    }

    public void setCountry_Code(long country_Code) {
        Country_Code = country_Code;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
