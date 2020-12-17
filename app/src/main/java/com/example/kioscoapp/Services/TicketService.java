package com.example.kioscoapp.Services;



import com.example.kioscoapp.ApiRest.ApiService;
import com.example.kioscoapp.ApiRest.Constants;
import com.example.kioscoapp.ApiRest.RestAdapter;
import com.example.kioscoapp.Model.ConsultCodBarMoneyCenter;
import com.example.kioscoapp.Model.ResponseCategories;
import com.example.kioscoapp.Model.Ticket;
import com.example.kioscoapp.Services.Local.CountryLocalService;

import java.util.ArrayList;

import retrofit2.Call;

public class TicketService {

    RestAdapter restApiAdapter;

    public TicketService() {
        this.restApiAdapter = new RestAdapter();
    }

    public Call<ArrayList<ConsultCodBarMoneyCenter>> saveTicket(Ticket ticket){
        ApiService service = restApiAdapter.getClientService();
        Call<ArrayList<ConsultCodBarMoneyCenter>> codBar = service.saveConsultAmountByServices(ticket);
        return codBar;
    }
}
