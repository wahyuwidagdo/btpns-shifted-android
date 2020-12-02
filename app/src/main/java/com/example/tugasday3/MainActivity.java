package com.example.tugasday3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
    int REQUEST_CODE_PERMITTION = 276;
    int REQUEST_CODE_FILE = 277;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        onClickGroup();
        initData();
//        if (shouldAskPermission()) {
            askPermission();
//        }
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

    protected boolean shouldAskPermission() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }
//    @TargetApi(23)
    protected void askPermission() {
        String[] permissions = {
                "android.permission.INTERNET",
                "android.permission.ACCESS_NETWORK_STATE",
        };
        Log.d("Halo", "1");
        ActivityCompat.requestPermissions(MainActivity.this, permissions, REQUEST_CODE_PERMITTION);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        try {
//            super.onActivityResult(requestCode, resultCode, data);
//            if (requestCode == REQUEST_CODE_FILE && resultCode == RESULT_OK) {
//                file_tv.setText(data.getData().toString());
//                file_iv.setImageURI(data.getData());
//            }
//        } catch (Exception ex) {
//            Toast.makeText(getApplicationContext(), ex.toString(),
//                    Toast.LENGTH_SHORT).show();
//        }
//    }

    @Override
    protected void onResume() {
        super.onResume();
        getListBerita("1", "20");
    }
}