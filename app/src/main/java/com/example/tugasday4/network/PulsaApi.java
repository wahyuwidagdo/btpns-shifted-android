package com.example.tugasday4.network;

import com.example.tugasday4.model.ProductResponse;
import com.example.tugasday4.model.ProductsResponse;
import com.example.tugasday4.model.Pulsa;
import com.example.tugasday4.model.PulsaResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PulsaApi {

    @GET("product")
    Call<ProductsResponse> getProductsList(@Query("page") String page,
                                           @Query("limit") String limit);
    @GET("nasabah/{id}")
    Call<ProductResponse> getProduct(@Path("id") String id);
    @POST("pulsa")
    Call<PulsaResponse> postPulsa(@Body Pulsa body);
}
