package com.example.tugasday4.network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.tugasday4.model.ProductResponse;
import com.example.tugasday4.model.ProductsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsRepository {

    private static ProductsRepository productsRepository;
    private PulsaApi pulsaApi;

    public static ProductsRepository getInstance(){
        if (productsRepository == null){
            productsRepository = new ProductsRepository();
        }
        return productsRepository;
    }

    public ProductsRepository(){
        pulsaApi = RetrofitService.cteateService(PulsaApi.class);
    }

    public MutableLiveData<ProductsResponse> getProducts(String page, String limit){
        MutableLiveData<ProductsResponse> productsData = new MutableLiveData<>();
        pulsaApi.getProductsList(page,limit).enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call,
                                   Response<ProductsResponse> response) {
                if (response.isSuccessful()){
                    productsData.setValue(response.body());
                    Log.d("Success", "onResponse: "+response.body());
                }
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                productsData.setValue(null);
                Log.d("Failed", "onResponse: "+null);
            }
        });
        return productsData;
    }

    public MutableLiveData<ProductResponse> getProduct(String id){
        MutableLiveData<ProductResponse> productData = new MutableLiveData<>();
        pulsaApi.getProduct(id).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call,
                                   Response<ProductResponse> response) {
                if (response.isSuccessful()){
                    productData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                productData.setValue(null);
            }
        });
        return productData;
    }
}
