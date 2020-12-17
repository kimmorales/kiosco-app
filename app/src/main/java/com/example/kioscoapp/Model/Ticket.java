package com.example.kioscoapp.Model;

import java.util.ArrayList;

public class Ticket {
    private String usuario;
    private String email;
    private String formatId;
    private String countryCode;
    private ArrayList<TicketRequest> ticketRequest;

    public Ticket() {
        this.usuario = "";
        this.email = "";
        this.formatId = "";
        this.countryCode = "";
        this.ticketRequest = new ArrayList<>();
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFormatId() {
        return formatId;
    }

    public void setFormatId(String formatId) {
        this.formatId = formatId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public ArrayList<TicketRequest> getTicketRequest() {
        return ticketRequest;
    }

    public void setTicketRequest(ArrayList<TicketRequest> ticketRequest) {
        this.ticketRequest = ticketRequest;
    }

    public void setTicketRequestByCar(ArrayList<ServicesByCarMoneyCenter> services){
        ArrayList<TicketRequest> ticketRequests=new ArrayList<>();
        TicketRequest ticketRequestService=new TicketRequest();
        TicketRequest ticketRequestRecharge=new TicketRequest();
        for (ServicesByCarMoneyCenter item:services) {
            if(item.isTiempoAire()){
                ticketRequestService.setIds(concatWithSeparator(ticketRequestService.getIds(),item.getTr_Id()));
                ticketRequestService.setAllowOpenPayment(concatWithSeparator(ticketRequestService.getAllowOpenPayment(),"0"));
                ticketRequestService.setAmounts(concatWithSeparator(ticketRequestService.getAmounts(),item.getAmount()));
                ticketRequestService.setServiceType("2");
            }else{
                ticketRequestRecharge.setIds(concatWithSeparator(ticketRequestRecharge.getIds(),item.getTr_Id()));
                ticketRequestRecharge.setAllowOpenPayment(concatWithSeparator(ticketRequestRecharge.getAllowOpenPayment(),"0"));
                ticketRequestRecharge.setAmounts(concatWithSeparator(ticketRequestRecharge.getAmounts(),item.getAmount()));
                ticketRequestRecharge.setServiceType("1");
            }
        }

        if(!ticketRequestRecharge.getIds().isEmpty()) { ticketRequests.add(ticketRequestRecharge); }
        if(!ticketRequestService.getIds().isEmpty()) { ticketRequests.add(ticketRequestService); }

        this.ticketRequest=ticketRequests;
    }

    private String concatWithSeparator(String text,String newText){
        String textAux=text;
        String separator=",";
        if (!text.equals("")) {
            textAux+=separator;
        }
        textAux+=newText;
        return textAux;
    }


}
