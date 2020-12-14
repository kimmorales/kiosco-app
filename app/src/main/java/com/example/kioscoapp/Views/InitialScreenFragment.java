package com.example.kioscoapp.Views;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.kioscoapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InitialScreenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InitialScreenFragment extends Fragment {

    OnListener mListener;
    Button btnContinueCategories;

    public InitialScreenFragment() {
        // Required empty public constructor
    }



    public static InitialScreenFragment newInstance() {
        InitialScreenFragment fragment = new InitialScreenFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof OnListener)
            mListener = (OnListener) getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_initial_screen, container, false);
        btnContinueCategories=v.findViewById(R.id.btnContinueCategories);
        btnContinueCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.goCategories();
            }
        });
        return  v;
    }

    public interface OnListener {
        void goCategories();
    }
}