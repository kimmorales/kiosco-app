package com.example.kioscoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.kioscoapp.Views.ProvidersActivity;

public class SelectedProviderActivity extends Fragment {
    private static final String PROVIDER_NAME = "param1";
    private static final String TAG_TITLE = "param2";
    private String providerName;
    private String tagTitle;
    TextView providerNameTextView, tagTitleTextView;
    Button buttonCheckPending ;

    public static SelectedProviderActivity newInstance(String providerName, String tagTitle) {
        SelectedProviderActivity fragment = new SelectedProviderActivity();
        Bundle args = new Bundle();
        args.putString(PROVIDER_NAME, providerName);
        args.putString(TAG_TITLE, tagTitle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            providerName = getArguments().getString(PROVIDER_NAME);
            tagTitle = getArguments().getString(TAG_TITLE);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.selected_provider_activity, container, false);
        providerNameTextView = v.findViewById(R.id.providerName);
        providerNameTextView.setText(providerName);
        tagTitleTextView = v.findViewById(R.id.tag_title);
        tagTitleTextView.setText(tagTitle);
      
        return  v;
    }

}
