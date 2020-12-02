package com.example.tugasday4.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tugasday4.model.Pulsa;
import com.example.tugasday4.model.PulsaResponse;
import com.example.tugasday4.network.PulsaRepository;

public class PulsaViewModel extends ViewModel {

    private PulsaRepository pulsaRepository;
    private MutableLiveData<PulsaResponse> mutableLiveData;

    public LiveData<PulsaResponse> postPulsaRepository(Pulsa pulsaPayload) {
        if (mutableLiveData == null) {
            pulsaRepository = PulsaRepository.getInstance();
        }
        mutableLiveData = pulsaRepository.postPulsa(pulsaPayload);

        return mutableLiveData;
    }
}
