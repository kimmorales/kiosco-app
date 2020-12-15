package com.example.kioscoapp.Views;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.kioscoapp.ApiRest.Constants;
import com.example.kioscoapp.R;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InactiveFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InactiveFragment extends Fragment {


    TextView textViewCount;
    Button buttonContinue;
    Button buttonLeave;
    OnListener onListener;
    CountDownTimer countDownTimer;

    public InactiveFragment() {
        // Required empty public constructor
    }


    public static InactiveFragment newInstance() {
        InactiveFragment fragment = new InactiveFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    private void startCounter(){
        countDownTimer = new CountDownTimer(Constants.COUNTER_INACTIVE, 1000) {
            public void onTick(long millisUntilFinished) {
                textViewCount.setText(String.format(Locale.getDefault(), "%d", millisUntilFinished / 1000L));
            }

            public void onFinish() {
                textViewCount.setText("Done.");
                leaveProcess();
            }
        }.start();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof OnListener)
            onListener = (OnListener) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_inactive, container, false);
        textViewCount=view.findViewById(R.id.tvCounter);
        buttonContinue=view.findViewById(R.id.btnContinueInactive);
        buttonLeave=view.findViewById(R.id.btnLeaveInactive);
        startCounter();
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onListener.restartTimer();
               //leaveProcess();
                onListener.continueProcess();
               // getActivity().getFragmentManager().popBackStack();
            }
        });

        buttonLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leaveProcess();
            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }

    private void leaveProcess(){
        onListener.leaveProcess(this);
    }

    public interface OnListener{
        void restartTimer();
        void leaveProcess(Fragment fragment);
        void continueProcess();
    }
}