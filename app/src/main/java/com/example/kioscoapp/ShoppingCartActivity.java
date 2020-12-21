package com.example.kioscoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kioscoapp.Adapter.CartAdapter;
import com.example.kioscoapp.Model.ConsultCodBarMoneyCenter;
import com.example.kioscoapp.Model.ServicesByCarMoneyCenter;
import com.example.kioscoapp.Model.Ticket;
import com.example.kioscoapp.Services.Local.CarLocalService;
import com.example.kioscoapp.Services.Local.CountryLocalService;
import com.example.kioscoapp.Services.TicketService;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingCartActivity extends AppCompatActivity {

    private ListView lvShoppingCart;
    private TextView textViewTotal,textViewServices;
    private Button btnGenerateCode,buttonBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        lvShoppingCart = (ListView) findViewById(R.id.listShopingCart);
        textViewTotal=findViewById(R.id.textViewTotal);
        btnGenerateCode=findViewById(R.id.btnGenerateCode);
        textViewServices=findViewById(R.id.txtServicesAmount);
        buttonBack=findViewById(R.id.buttonGoBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnGenerateCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTicket();
            }
        });

        getDataCar();
    }

    private void getDataCar(){
        CarLocalService carLocalService= new CarLocalService(this);
        ArrayList<ServicesByCarMoneyCenter> services= carLocalService.getItemsCar();
        getTotal(services);
        lvShoppingCart.setAdapter(new CartAdapter(this, services, new CartAdapter.OnListener() {
            @Override
            public void onClickDelete(ServicesByCarMoneyCenter services) {
                deleteService(services);
            }
        }));
    }

    private void getTotal(ArrayList<ServicesByCarMoneyCenter> services){
        double acum=0;
        int i=0;
        for (ServicesByCarMoneyCenter service:services
             ) {
            i++;
            acum+=Double.parseDouble(service.getAmount());
        }
        textViewServices.setText(String.valueOf(i)+" Servicios");
        textViewTotal.setText(String.valueOf(acum));
    }

    private void saveTicket(){

        CarLocalService localService=new CarLocalService(this);
        CountryLocalService localVariable=new CountryLocalService(this);
        ArrayList<ServicesByCarMoneyCenter> items= localService.getItemsCar();
        if(items.size()>0){
            final ProgressDialog dialog = ProgressDialog.show(this, "",
                    "Generando Ticket...", true);
            Ticket ticket=new Ticket();
            ticket.setEmail("");
            ticket.setCountryCode(items.get(0).getCountryCode());
            ticket.setFormatId(localVariable.getFormat());
            ticket.setUsuario("KioskoApp");
            ticket.setTicketRequestByCar(items);
            TicketService ticketService=new TicketService();
            ticketService.saveTicket(ticket).enqueue(new Callback<ArrayList<ConsultCodBarMoneyCenter>>() {
                @Override
                public void onResponse(Call<ArrayList<ConsultCodBarMoneyCenter>> call, Response<ArrayList<ConsultCodBarMoneyCenter>> response) {
                    System.out.println(response.body().toString());
                    goToPaymentCode(response.body());
                    dialog.cancel();
                }

                @Override
                public void onFailure(Call<ArrayList<ConsultCodBarMoneyCenter>> call, Throwable t) {
                    System.out.println(t.getMessage());
                    dialog.cancel();
                }
            });
        }else{
            Toast.makeText(this, "Añada porfavor servicios al carrito", Toast.LENGTH_LONG).show();
        }

    }

    private void goToPaymentCode(ArrayList<ConsultCodBarMoneyCenter> codes){
        Intent intent = new Intent(this,PaymentCodeActivity.class);
        for(int j=0;j<codes.size();j++) {
            List<ConsultCodBarMoneyCenter.CodBars> codBars = codes.get(j).getCodBars();
            for (int i = 0; i < codBars.size(); i++) {
                if (codBars.get(i).getServiceType() == 1) {
                    intent.putExtra("codService", codBars.get(i).getBarcodeimg());
                    intent.putExtra("idBarcodeService", codBars.get(i).getIdBarcode());
                } else {
                    intent.putExtra("codRecharge", codBars.get(i).getBarcodeimg());
                    intent.putExtra("idBarcodeCharge", codBars.get(i).getIdBarcode());
                }
            }
        }
        intent.putExtra("dateExp",codes.get(0).getExpirantionDateCodBar());
        startActivity(intent);
    }



    private void deleteService(ServicesByCarMoneyCenter service){
        boolean flag=true;
        CarLocalService carLocalService= new CarLocalService(this);
        ArrayList<ServicesByCarMoneyCenter> services=carLocalService.getItemsCar();
        if (service.getPosition()>-1){
            for (ServicesByCarMoneyCenter item:services
            ) {
                flag=item.isPossibleToDelete(service.getPosition());
                if(!flag){
                    break;
                }
            }
        }else{
            flag=true;
        }


        if(flag){
            ArrayList<ServicesByCarMoneyCenter> servicesd=carLocalService.deleteServicesCar(service);
            getTotal(servicesd);
            lvShoppingCart.setAdapter(new CartAdapter(this, servicesd, new CartAdapter.OnListener() {
                @Override
                public void onClickDelete(ServicesByCarMoneyCenter services) {
                    deleteService(services);
                }
            }));
        }else{
            Toast.makeText(this,"Debe eliminar el carrito de facturas en orden cronologico de la mas reciente a mas antigua", Toast.LENGTH_LONG).show();
        }

    }


}
