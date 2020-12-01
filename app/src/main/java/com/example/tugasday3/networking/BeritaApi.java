package com.example.tugasday3.networking;

import com.example.tugasday3.model.Berita;
import com.example.tugasday3.model.BeritaResponse;
import com.example.tugasday3.model.BeritasResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BeritaApi {

    @GET("berita")
    Call<BeritasResponse> getBeritasList(@Query("page") String page,
                                                   @Query("limit") String limit);

    @POST("berita")
    Call<BeritaResponse> postBerita(@Body Berita body);

//    @GET("berita/{id}")
//    Call<BeritaResponse> getBerita(@Path("id") String id);
//
//    @PUT("berita/{id}")
//    Call<BeritaResponse> putBerita(@Path("id") String id, @Body Berita body);
//
//    @DELETE("berita/{id}")
//    Call<BeritaResponse> deleteBerita(@Path("id") String id);
}
