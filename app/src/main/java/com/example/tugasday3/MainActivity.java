package com.example.tugasday3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tugasday3.adapter.BeritaAdapter;
import com.example.tugasday3.model.Berita;
import com.example.tugasday3.viewmodels.BeritaViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Berita> beritaArrayList = new ArrayList<>();
    BeritaAdapter beritaAdapter;
    RecyclerView rvBerita;
    BeritaViewModel beritaViewModel;
    FloatingActionButton floatingActionButton;
    List<Berita> beritas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        onClickGroup();
        initData();
    }

    void findViewById(){
        rvBerita = findViewById(R.id.beritaRecyclerView);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab1);
    }

    private void initData() {
        if (beritaAdapter == null) {
            beritaAdapter = new BeritaAdapter(MainActivity.this, beritaArrayList);
            rvBerita.setLayoutManager(new LinearLayoutManager(this));
            rvBerita.setAdapter(beritaAdapter);
            rvBerita.setItemAnimator(new DefaultItemAnimator());
            rvBerita.setNestedScrollingEnabled(true);
        } else {
            beritaAdapter.notifyDataSetChanged();
        }
        beritaViewModel = ViewModelProviders.of(this).get(BeritaViewModel.class);

        beritaViewModel.init();
        beritaViewModel.getBeritasRepository().observe(this, beritashResponse -> {
            beritas = beritashResponse.getData();
            beritaArrayList.clear();
            beritaArrayList.addAll(beritas);
            beritaAdapter.notifyDataSetChanged();
        });
    }

    private void getListBerita(String page, String limit ) {
        beritaViewModel.refresh(page, limit);
        beritaViewModel.getBeritasRepository().observe(this, beritasResponse -> {
            beritas = beritasResponse.getData();
            beritaArrayList.clear();
            beritaArrayList.addAll(beritas);
            beritaAdapter.notifyDataSetChanged();
        });
    }

    void onClickGroup(){
//        refreshTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getListNasabah("1","10");
//            }
//        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("mode", "add");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getListBerita("1", "20");
    }
}