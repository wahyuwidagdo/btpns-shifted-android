package com.example.tugasday2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tugasday2.adapter.BeritaAdapter;
import com.example.tugasday2.model.Berita;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView beritaListView;
    Button addBeritaButton;
    BeritaAdapter beritaAdapter;
    private List<Berita> listBerita = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        onClickGroup();
        initData();
    }

    void findViewById() {
        beritaListView = (ListView) findViewById(R.id.beritaListView);
        addBeritaButton = (Button) findViewById(R.id.addBeritaButton);
    }

    void initData() {
        beritaAdapter = new BeritaAdapter(getApplicationContext(), listBerita);
        beritaListView.setAdapter(beritaAdapter);
        beritaAdapter.notifyDataSetChanged();
    }

    void onClickGroup() {
        addBeritaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddBeritaActivity.class);
                startActivity(intent);
            }
        });
    }
}