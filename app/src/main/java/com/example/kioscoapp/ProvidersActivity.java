package com.example.kioscoapp;



import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kioscoapp.Model.ResponseCategories;
import com.example.kioscoapp.Model.ResponseServicesByName;
import com.example.kioscoapp.Services.CategoryMoneyCenterService;
import com.example.kioscoapp.Services.ProviderServiceByName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProvidersActivity extends AppCompatActivity {
    public static final String SELECTED_INDEX = "";
    public static final Integer SELECTED_IMAGE = null;
    ListView list;
    String selectedIndex = "0";

    String[] maintitle ={
            "Kolbi CR","AYA", "Claro"
    };


    Integer[] imgid={
            R.drawable.download_1,R.drawable.download_2,
            R.drawable.download_3,R.drawable.download_1,
            R.drawable.download_2,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.providers_list_activity);

        ProviderItem adapter = new ProviderItem(this, maintitle,imgid);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        getCategories();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                // TODO Auto-generated method stub
                    //code specific to first list item
                    selectedIndex = maintitle[position];

                    sendMessage(view);
                    Toast.makeText(getApplicationContext(),maintitle[position],Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, SelectedProviderActivity.class);
        //EditText editText = (EditText) findViewById(R.id.editText);
       // String message = editText.getText().toString();
        intent.putExtra(SELECTED_INDEX, selectedIndex);
        //intent.putExtra(String.valueOf(SELECTED_IMAGE), imgid[0]);
        startActivity(intent);
    }

    public  void getCategories(){
        ProviderServiceByName categorySer=new ProviderServiceByName();
        categorySer.loadServicesByName(this).enqueue(new Callback<ResponseServicesByName>() {
            @Override
            public void onResponse(Call<ResponseServicesByName> call, Response<ResponseServicesByName> response) {
                if( response.body() != null){
                    if(response.isSuccessful() && response.body().getData().size()>0){
                        //setCategories(response.body().getData());
                    }
                }


            }

            @Override
            public void onFailure(Call<ResponseServicesByName> call, Throwable t) {
                System.out.println("error");
            }
        });
    }
}