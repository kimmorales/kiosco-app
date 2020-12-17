package com.example.kioscoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.kioscoapp.ApiRest.Constants;
import com.example.kioscoapp.Services.Local.CarLocalService;
import com.example.kioscoapp.Services.Local.CountryLocalService;
import com.example.kioscoapp.Services.Timer.MyCountDownTimer;
import com.example.kioscoapp.Utils.Utils;
import com.example.kioscoapp.Views.CategoriesFragment;
import com.example.kioscoapp.Views.CountrySelectedFragment;
import com.example.kioscoapp.Views.InactiveFragment;
import com.example.kioscoapp.Views.InitialScreenFragment;
import com.example.kioscoapp.Views.NotFoundFragment;
import com.example.kioscoapp.Views.ProvidersActivity;
import com.example.kioscoapp.Views.ServicesConsultFragment;
import com.example.kioscoapp.Views.TiempoAireFragment;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements
        CountrySelectedFragment.OnListener,
        InitialScreenFragment.OnListener,
        CategoriesFragment.OnListener,
        ProvidersActivity.OnListener,
        TiempoAireFragment.OnListener,
        SelectedProviderActivity.OnListener,
        ServicesConsultFragment.OnListener,
        InactiveFragment.OnListener{

    Toolbar toolbar;
    TextView textCartItemCount;
    Timer timer = new Timer();
    final Handler handler = new Handler();
    MyCountDownTimer counter;
    private final long interval = 1 * 1000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.tbMain);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              backStack();
            }
        });


        validateSelectedCountry();
        startCounter();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if(item.getItemId()==R.id.action_cart)
        {
            goToShoppingCar();
        }
        return false;
    }

    private void backStack(){
        int count = getFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }
    }

    private void startCounter(){
        this.counter=new MyCountDownTimer(Constants.MILISECONDS_MAX_INACTIVE, interval, new MyCountDownTimer.OnListener() {
            @Override
            public void onFinishCounter() {
                startInactive();
            }
        });
    }



    @Override
    protected void onRestart() {
        super.onRestart();
        try{
            this.counter.start();
            this.changeTotalToolbar();
        }catch (NullPointerException e){

        }

    }



    @Override
    protected void onStop() {
        super.onStop();
        this.counter.cancel();
    }


    @Override
    public void onUserInteraction(){

        super.onUserInteraction();

        //Reset the timer on user interaction...
        this.counter.cancel();
        this.counter.start();
    }

    public void startTask(){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            //Ejecuta tu AsyncTask!
                            AsyncTask myTask = new AsyncTask() {
                                @Override
                                protected Object doInBackground(Object[] objects) {
                                    startInactive();
                                    return null;
                                }
                            };

                            myTask.execute();
                        } catch (Exception e) {
                            Log.e("error", e.getMessage());
                        }
                    }
                });
            }
        };

        timer.schedule(task, 0, 20000);

    }

    private void startInactive(){
        //timer.cancel();
       changeFragment(InactiveFragment.newInstance(),true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_cart);

        View actionView = menuItem.getActionView();
        textCartItemCount = (TextView) actionView.findViewById(R.id.cart_badge);

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });



        return true;
    }

    private void goToShoppingCar(){
        Intent i = new Intent(this, ShoppingCartActivity.class);
        startActivity(i);
    }


    public void validateSelectedCountry(){
        CountryLocalService localService= new CountryLocalService(this);
        if(localService.getCountry() !="0" && localService.getFormat()!="0"){
            changeFragment(InitialScreenFragment.newInstance(),false);
        }else{
            changeFragment(CountrySelectedFragment.newInstance(),false);
        }
    }


    @Override
    public void goInitScreen() {
        changeFragment(InitialScreenFragment.newInstance(),true);
    }

    @Override
    public void goCategories() {
        changeFragment(CategoriesFragment.newInstance("",""),true);
    }

    public void changeFragment(Fragment fragment,boolean stack){
        //startTask();
        if(stack){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flMainContainer, fragment)
                    .addToBackStack(null)
                    .commit();
        }else{
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flMainContainer, fragment)
                    .commit();
        }

    }


    @Override
    public void goProvidersActivity(String id, String categoryName) {
        changeFragment(ProvidersActivity.newInstance(id,categoryName),true);
    }

    @Override
    public void goToSelectedProvider(String providerName, String tagTitle,String isTiempoAire,
                                     String openPayment, String countryCode, String commerceIdWm, String name) {
        changeFragment(SelectedProviderActivity.newInstance(providerName,tagTitle, isTiempoAire,
                openPayment, countryCode, commerceIdWm, name),true);
    }

    @Override
    public void goToTiempoAire(String serviceName,String providerName, String countryCode, String commerceId, String invoceNumber, Boolean openPayment){
        changeFragment(TiempoAireFragment.newInstance(serviceName,providerName,countryCode, commerceId, invoceNumber,openPayment),true);
    }

    @Override
    public void goToServicesConsult(String countryCode,String commerceId, String serviceName,String providerName, String invoiceNumber) {
        changeFragment(ServicesConsultFragment.newInstance(  countryCode, commerceId,serviceName, providerName,  invoiceNumber),true);
    }

    @Override
    public void goToNotFound() {
        changeFragment(new NotFoundFragment(),true);
    }

    @Override
    public void changeTotalConsult() {
        changeTotalToolbar();
    }

    private void changeTotalToolbar(){

        try {
            CarLocalService carLocalService=new CarLocalService(this);
            String value=carLocalService.getTotalItems();
            textCartItemCount.setText(value);
        }catch (NullPointerException e){
            textCartItemCount.setText("0");
        }


    }

    @Override
    public void restartTimer() {
        this.counter.cancel();
        this.counter.start();
    }

    @Override
    public void leaveProcess(Fragment fragment) {
        CarLocalService carLocalService=new CarLocalService(this);
        carLocalService.clearCar();
        textCartItemCount.setText("0");
        FragmentManager fm = getSupportFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
        changeFragment(InitialScreenFragment.newInstance(),false);
    }

    @Override
    public void continueProcess() {
        backStack();
    }

    @Override
    public void changeTotalTiempoAire() {
        changeTotalToolbar();
    }
}