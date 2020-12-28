package com.example.kioscoapp.Remittances;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.kioscoapp.R;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;
import java.util.List;

public class RemittancesActivity extends AppCompatActivity implements SourceOfFoundFragment.OnListener {

    StepView stepView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remittances);
        stepView = findViewById(R.id.step_view);
        setSteps();
        changeFragment(SourceOfFoundFragment.newInstance("",""),false);
    }

    private void setSteps(){
        List<String> steps=new ArrayList<>();
        steps.add("Paso 1");
        steps.add("Paso 2");
        steps.add("Paso 3");
        stepView.setSteps(steps);
        stepView.go(3,true);
    }

    private void changeFragment(Fragment fragment, boolean stack){
        //startTask();
        if(stack){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flRemittances, fragment)
                    .addToBackStack(null)
                    .commit();
        }else{
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flRemittances, fragment)
                    .commit();
        }

    }

    @Override
    public void goSummary() {
        changeFragment(SummaryRemittanceSendFragment.newInstance("",""),false);
    }
}