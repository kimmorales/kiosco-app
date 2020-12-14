package com.example.kioscoapp.Model;

public class Format {
    private long Format_Id;
    private String Format_Name;

    public long getId() {
        return Format_Id;
    }

    public void setId(long id) {
        this.Format_Id = id;
    }

    public String getName() {
        return Format_Name;
    }

    public void setName(String name) {
        this.Format_Name = name;
    }
}
