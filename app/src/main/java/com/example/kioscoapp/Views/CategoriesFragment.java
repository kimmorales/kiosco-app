package com.example.kioscoapp.Views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;

import com.example.kioscoapp.Adapter.CategoriesAdapter;
import com.example.kioscoapp.Model.CategoriesMoneyCenter;
import com.example.kioscoapp.Model.ResponseCategories;
import com.example.kioscoapp.R;
import com.example.kioscoapp.Services.CategoryMoneyCenterService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoriesFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView recyclerViewCat;
    EditText searchView;


    private String mParam1;
    private String mParam2;

    public CategoriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoriesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoriesFragment newInstance(String param1, String param2) {
        CategoriesFragment fragment = new CategoriesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public  void getCategories(){
        CategoryMoneyCenterService categorySer=new CategoryMoneyCenterService();
        categorySer.loadCategories(getContext()).enqueue(new Callback<ResponseCategories>() {
            @Override
            public void onResponse(Call<ResponseCategories> call, Response<ResponseCategories> response) {
                if(response.isSuccessful() && response.body().getData().size()>0){
                    setCategories(response.body().getData());
                }

            }

            @Override
            public void onFailure(Call<ResponseCategories> call, Throwable t) {
                System.out.println("error");
            }
        });
    }

    private void setCategories(ArrayList<CategoriesMoneyCenter> categories){
        recyclerViewCat.setAdapter(new CategoriesAdapter(getContext(),categories));
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_categories, container, false);
        recyclerViewCat=v.findViewById(R.id.rvCategories);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerViewCat.setHasFixedSize(true);
        recyclerViewCat.setLayoutManager(layoutManager);
        searchView=v.findViewById(R.id.svCategories);
        getCategories();
        return  v;
    }
}