package com.example.kioscoapp.Services;

        import android.content.Context;

        import com.example.kioscoapp.ApiRest.ApiService;
        import com.example.kioscoapp.ApiRest.Constants;
        import com.example.kioscoapp.ApiRest.RestAdapter;
        import com.example.kioscoapp.Model.ResponseCategories;
        import com.example.kioscoapp.Model.ResponseServicesByName;
        import com.example.kioscoapp.Model.ServiceByNameMoneyCenter;
        import com.example.kioscoapp.Services.Local.CountryLocalService;

        import okhttp3.Callback;
        import retrofit2.Call;

public class ProviderServiceByName {
    RestAdapter restApiAdapter;

    public ProviderServiceByName() {
        this.restApiAdapter = new RestAdapter();
    }

    public Call<ResponseServicesByName> loadServicesByName(Context context, String categoryId){
        ApiService service = restApiAdapter.getClientService();
        CountryLocalService localService= new CountryLocalService(context);
        Call<ResponseServicesByName> categories = service.getServicesByName(localService.getCountry(),localService.getFormat(),categoryId,"1", Constants.ENV);
        return categories;
    }
}

