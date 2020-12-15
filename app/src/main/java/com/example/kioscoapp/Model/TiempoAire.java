package com.example.kioscoapp.Model;

public class TiempoAire {

 /*"ItemNumber": 1853,
         "Service_Id": "10002",
         "Description": "Kolbi CRC",
         "Amount": 500,
         "Country_Code": "CRC",
         "Format_Id": 1,
         "Format_Name": "Walmart",
         "dateCreate": "2020-12-14T14:31:09.687Z"*/

    private String ItemNumber;
    private String Service_Id;
    private String Description;
    private int Amount;
    private String Country_Code;
    private String Format_Id;
    private String Format_Name;
    private String dateCreate;

    public String getItemNumber() {
        return ItemNumber;
    }

    public void setItemNumber(String itemNumber) {
        ItemNumber = itemNumber;
    }

    public String getService_Id() {
        return Service_Id;
    }

    public void setService_Id(String service_Id) {
        Service_Id = service_Id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getCountry_Code() {
        return Country_Code;
    }

    public void setCountry_Code(String country_Code) {
        Country_Code = country_Code;
    }

    public String getFormat_Id() {
        return Format_Id;
    }

    public void setFormat_Id(String format_Id) {
        Format_Id = format_Id;
    }

    public String getFormat_Name() {
        return Format_Name;
    }

    public void setFormat_Name(String format_Name) {
        Format_Name = format_Name;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }
}

