package com.example.kioscoapp.Remittances;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.kioscoapp.R;
import com.example.kioscoapp.Views.InitialScreenFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SourceOfFoundFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SourceOfFoundFragment extends Fragment {

    OnListener mlistener;
    Button btnValidate;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SourceOfFoundFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SourceOfFoundFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SourceOfFoundFragment newInstance(String param1, String param2) {
        SourceOfFoundFragment fragment = new SourceOfFoundFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof OnListener)
            mlistener = (OnListener) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_source_of_found, container, false);
        btnValidate=v.findViewById(R.id.btnValidateFounds);
        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
        return v;
    }

    private void validate(){
        mlistener.goSummary();
    }

    public interface OnListener{
        void goSummary();
    }
}