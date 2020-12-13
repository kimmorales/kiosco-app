package com.example.kioscoapp.Model;

public class CategoriesMoneyCenter {
    private long Category_Id;
    private String Name;
    private String Logo;
    private String Country_Code_Home;
    private long Format_id;

    public long getCategory_Id() {
        return Category_Id;
    }

    public void setCategory_Id(long category_Id) {
        Category_Id = category_Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
    }

    public String getCountry_Code_Home() {
        return Country_Code_Home;
    }

    public void setCountry_Code_Home(String country_Code_Home) {
        Country_Code_Home = country_Code_Home;
    }

    public long getFormat_id() {
        return Format_id;
    }

    public void setFormat_id(long format_id) {
        Format_id = format_id;
    }
}
