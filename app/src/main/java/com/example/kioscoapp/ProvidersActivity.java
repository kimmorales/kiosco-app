package com.example.kioscoapp;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProvidersActivity extends AppCompatActivity {
    public static final String SELECTED_INDEX = "";
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
        setContentView(R.layout.activity_main);

        MyListView adapter = new MyListView(this, maintitle,imgid);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);


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
        startActivity(intent);
    }
}