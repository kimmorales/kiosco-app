package com.example.kioscoapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ServiceByNameMoneyCenter implements Parcelable {
    private  String Country_Code;
    private long  Format_Id;
    private  String Commerce_Id_WM;
    private  String Logo;
    private  String Description;
    private  String Name;
    private  String Tag_Input_Data;
    private  String Mask;
    private  String Open_Payment;
    private  String Visible_App;
    private  String Visible_Kiosko;
    private  String Is_Tiempo_Aire;
    private  String respondeCode;

    public ServiceByNameMoneyCenter(Parcel in) {
        super();
        readFromParcel(in);
    }

    public static final Parcelable.Creator<ServiceByNameMoneyCenter> CREATOR = new Parcelable.Creator<ServiceByNameMoneyCenter>() {
        public ServiceByNameMoneyCenter createFromParcel(Parcel in) {
            return new ServiceByNameMoneyCenter(in);
        }
        public ServiceByNameMoneyCenter[] newArray(int size) {
            return new ServiceByNameMoneyCenter[size];
        }
    };

    public String getCountry_Code() {
        return Country_Code;
    }

    public void setCountry_Code(String country_Code) {
        Country_Code = country_Code;
    }

    public long getFormat_Id() {
        return Format_Id;
    }

    public void setFormat_Id(long format_Id) {
        Format_Id = format_Id;
    }

    public String getCommerce_Id_WM() {
        return Commerce_Id_WM;
    }

    public void setCommerce_Id_WM(String commerce_Id_WM) {
        Commerce_Id_WM = commerce_Id_WM;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTag_Input_Data() {
        return Tag_Input_Data;
    }

    public void setTag_Input_Data(String tag_Input_Data) {
        Tag_Input_Data = tag_Input_Data;
    }

    public String getMask() {
        return Mask;
    }

    public void setMask(String mask) {
        Mask = mask;
    }

    public String getOpen_Payment() {
        return Open_Payment;
    }

    public void setOpen_Payment(String open_Payment) {
        Open_Payment = open_Payment;
    }

    public String getVisible_App() {
        return Visible_App;
    }

    public void setVisible_App(String visible_App) {
        Visible_App = visible_App;
    }

    public String getVisible_Kiosko() {
        return Visible_Kiosko;
    }

    public void setVisible_Kiosko(String visible_Kiosko) {
        Visible_Kiosko = visible_Kiosko;
    }

    public String getIs_Tiempo_Aire() {
        return Is_Tiempo_Aire;
    }

    public void setIs_Tiempo_Aire(String is_Tiempo_Aire) {
        Is_Tiempo_Aire = is_Tiempo_Aire;
    }

    public String getRespondeCode() {
        return respondeCode;
    }

    public void setRespondeCode(String respondeCode) {
        this.respondeCode = respondeCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(Country_Code);
        dest.writeLong(Format_Id);
        dest.writeString(Commerce_Id_WM);
        dest.writeString(Logo);
        dest.writeString(Description);
        dest.writeString(Name);
        dest.writeString(Tag_Input_Data);
        dest.writeString(Mask);
        dest.writeString(Open_Payment);
        dest.writeString(Visible_App);
        dest.writeString(Visible_Kiosko);
        dest.writeString(Is_Tiempo_Aire);
        dest.writeString(respondeCode);

    }

    public void readFromParcel(Parcel in) {
        Country_Code=in.readString();
        Format_Id=in.readLong();
        Commerce_Id_WM=in.readString();
        Logo=in.readString();
        Description=in.readString();
        Name=in.readString();
        Tag_Input_Data=in.readString();
        Mask=in.readString();
        Open_Payment=in.readString();
        Visible_App=in.readString();
        Visible_Kiosko=in.readString();
        Is_Tiempo_Aire=in.readString();
        respondeCode=in.readString();
    }
}
