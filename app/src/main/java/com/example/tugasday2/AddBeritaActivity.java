package com.example.tugasday2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.tugasday2.adapter.BeritaAdapter;
import com.example.tugasday2.model.Berita;

import java.util.ArrayList;
import java.util.List;

public class AddBeritaActivity extends AppCompatActivity {

    ListView beritaListView;
    BeritaAdapter beritaAdapter;
    private List<Berita> listBerita = new ArrayList<>();
    private Button addBeritaButton;
    private EditText judulEditText, kategoriEditText, urlImageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_berita);
        findViewById();
        onClickGroup();
    }

    void findViewById() {
        beritaListView = (ListView) findViewById(R.id.beritaListView);
        addBeritaButton = (Button) findViewById(R.id.addBeritaButton);
        judulEditText = findViewById(R.id.judulEditText);
        kategoriEditText = findViewById(R.id.kategoriEditText);
        urlImageEditText = findViewById(R.id.urlImageEditText);
    }

    void onClickGroup() {
        addBeritaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddBeritaActivity.this, MainActivity.class);
                String judul = judulEditText.getText().toString();
                String kategori = kategoriEditText.getText().toString();
                String urlImage = urlImageEditText.getText().toString();
                Berita newBerita = new Berita();
                newBerita.setJudul(judul);
                newBerita.setKategori(kategori);
                newBerita.setUrlImage(urlImage);
                listBerita.add(newBerita);

                beritaAdapter.notifyDataSetChanged();
                startActivity(intent);
                finish();
            }
        });
    }
}