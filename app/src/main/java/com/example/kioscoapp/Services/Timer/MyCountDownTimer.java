package com.example.kioscoapp.Services.Timer;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;

import com.example.kioscoapp.ShoppingCartActivity;

public class MyCountDownTimer  extends CountDownTimer {

    OnListener mListener;

    public MyCountDownTimer(long millisInFuture, long countDownInterval,OnListener listener) {
        super(millisInFuture, countDownInterval);
       this.mListener=listener;
    }

    @Override
    public void onTick(long l) {

    }

    @Override
    public void onFinish() {
        mListener.onFinishCounter();
    }

    public interface OnListener{
        void onFinishCounter();
    }
}
