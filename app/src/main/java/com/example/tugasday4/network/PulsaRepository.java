package com.example.tugasday4.network;

import androidx.lifecycle.MutableLiveData;

import com.example.tugasday4.model.Pulsa;
import com.example.tugasday4.model.PulsaResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PulsaRepository {

    private static PulsaRepository pulsaRepository;
    private PulsaApi pulsaApi;

    public static PulsaRepository getInstance(){
        if (pulsaRepository == null){
            pulsaRepository = new PulsaRepository();
        }
        return pulsaRepository;
    }

    public PulsaRepository(){
        pulsaApi = RetrofitService.cteateService(PulsaApi.class);
    }

    public MutableLiveData<PulsaResponse> postPulsa(Pulsa pulsaPayload){
        MutableLiveData<PulsaResponse> nasabahData = new MutableLiveData<>();
        pulsaApi.postPulsa(pulsaPayload).enqueue(new Callback<PulsaResponse>() {
            @Override
            public void onResponse(Call<PulsaResponse> call,
                                   Response<PulsaResponse> response) {
                if (response.isSuccessful()){
                    nasabahData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<PulsaResponse> call, Throwable t) {
                nasabahData.setValue(null);
            }
        });
        return nasabahData;
    }
}
