package com.example.kioscoapp.Mappers;

import com.example.kioscoapp.Model.Consult;
import com.example.kioscoapp.Model.ServicesByCarMoneyCenter;
import com.example.kioscoapp.Model.TiempoAire;
import com.example.kioscoapp.Services.ServiceConsult;

public class Mappers {

    public static ServicesByCarMoneyCenter Map(Consult consult,String invoiceNumber,String serviceName,boolean openPayment,String currency,int position){
        ServicesByCarMoneyCenter services=new ServicesByCarMoneyCenter();
        services.setCurrency("");//TODO Cambiar a la moneda que se almacene localmente
        services.setAmount(consult.getAmount());
        services.setServiceName(serviceName);
        services.setAccountNumber(invoiceNumber);//TODO PREGUNTAR
        services.setCountryCode(consult.getCountryCode());
        services.setAllowOpenPayment(String.valueOf(openPayment));
        services.setServiceId("1");//TODO poner variable desde lo elegido anteriormete
        services.setCustomerName(consult.getCustomerName());
        services.setFormatId(consult.getFormatId());
        services.setPosition(position);
        services.setServiceType("1");//TODO cambiar
        services.setUrlImgService("");//TODO cambiar
        services.setDateCreate(consult.getDateCreate());
        services.setDateExpiration(consult.getDateExpiration());
        services.setTr_Id(consult.getTr_Id());
        services.setTiempoAire(false);
        return services;
    }

    public static ServicesByCarMoneyCenter Map(TiempoAire consult,String invoiceNumber,String serviceName,boolean openPayment,String currency){
        ServicesByCarMoneyCenter services=new ServicesByCarMoneyCenter();
        services.setCurrency("");//TODO Cambiar a la moneda que se almacene localmente
        services.setAmount(String.valueOf(consult.getAmount()));
        services.setAccountNumber(invoiceNumber);//TODO PREGUNTAR
        services.setServiceName(serviceName);
        services.setPosition(-1);
        services.setCountryCode(consult.getCountry_Code());
        services.setAllowOpenPayment(String.valueOf(openPayment));
        services.setServiceId(consult.getService_Id());
        services.setCustomerName("");//TODO PREGUNTAR
        services.setFormatId(consult.getFormat_Id());
        services.setServiceType("2");//TODO cambiar
        services.setUrlImgService("");//TODO cambiar
        services.setDateCreate(consult.getDateCreate());
        services.setDateExpiration("");//TODO PREGUNTAR
        services.setTr_Id(consult.getItemNumber());
        services.setTiempoAire(true);
        return services;
    }
}
