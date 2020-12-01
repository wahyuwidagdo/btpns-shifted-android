package com.example.tugasday3.networking;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.tugasday3.model.Berita;
import com.example.tugasday3.model.BeritaResponse;
import com.example.tugasday3.model.BeritasResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeritasRepository {

    private static BeritasRepository beritasRepository;

    public static BeritasRepository getInstance() {
        if (beritasRepository == null) {
            beritasRepository = new BeritasRepository();
        }
        return beritasRepository;
    }

    private BeritaApi beritaApi;

    public BeritasRepository() {
        beritaApi = RetrofitService.createService(BeritaApi.class);
    }

    public MutableLiveData<BeritasResponse> getBeritas(String page, String limit) {
        MutableLiveData<BeritasResponse> beritasData = new MutableLiveData<>();
        beritaApi.getBeritasList(page, limit).enqueue(new Callback<BeritasResponse>() {
            @Override
            public void onResponse(Call<BeritasResponse> call, Response<BeritasResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("Succeess", response.body().toString());
                    beritasData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BeritasResponse> call, Throwable t) {
                beritasData.setValue(null);
            }
        });
        return beritasData;
    }

    public MutableLiveData<BeritaResponse> postNasabah(Berita nasabahPayload){
        MutableLiveData<BeritaResponse> beritaData = new MutableLiveData<>();
        beritaApi.postBerita(nasabahPayload).enqueue(new Callback<BeritaResponse>() {
            @Override
            public void onResponse(Call<BeritaResponse> call,
                                   Response<BeritaResponse> response) {
                if (response.isSuccessful()){
                    beritaData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BeritaResponse> call, Throwable t) {
                beritaData.setValue(null);
            }
        });
        return beritaData;
    }
}
