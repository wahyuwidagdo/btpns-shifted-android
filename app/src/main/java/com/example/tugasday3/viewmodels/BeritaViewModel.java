package com.example.tugasday3.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tugasday3.model.Berita;
import com.example.tugasday3.model.BeritaResponse;
import com.example.tugasday3.model.BeritasResponse;
import com.example.tugasday3.networking.BeritasRepository;

public class BeritaViewModel extends ViewModel {

    private MutableLiveData<BeritasResponse> mutableLiveData;
    private BeritasRepository beritasRepository;
    private MutableLiveData<BeritaResponse> mutableBeritaLiveData;

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        beritasRepository = BeritasRepository.getInstance();
        mutableLiveData = beritasRepository.getBeritas("1", "10");
    }

    public LiveData<BeritasResponse> getBeritasRepository() {
        return mutableLiveData;
    }

    public void refresh(String page, String limit ){
        if (mutableLiveData != null){
            mutableLiveData = beritasRepository.getBeritas(page, limit);
            return;
        }
        beritasRepository = BeritasRepository.getInstance();
        mutableLiveData = beritasRepository.getBeritas("1", "10");
    }

//    public LiveData<BeritaResponse> getBeritaRepository(String id) {
//        if (mutableBeritaLiveData == null){
//            beritasRepository = BeritasRepository.getInstance();
//            mutableBeritaLiveData = beritasRepository.getBerita(id);
//        }
//        return mutableBeritaLiveData;
//    }

    public LiveData<BeritaResponse> postBeritaRepository(Berita beritaPayload) {
        if (mutableBeritaLiveData == null) {
            beritasRepository = BeritasRepository.getInstance();
        }
        mutableBeritaLiveData = beritasRepository.postNasabah(beritaPayload);

        return mutableBeritaLiveData;
    }
}
