package com.example.tugasday3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tugasday3.model.Berita;
import com.example.tugasday3.viewmodels.BeritaViewModel;

public class ViewActivity extends AppCompatActivity {

    private String id,judul, kategori, urlImage;
    private Berita berita=new Berita();

    EditText judulEditText, kategoriEditText, urlImageEditText;
    Button saveButton, deleteButton;
    String mode="add";
    BeritaViewModel beritaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        findViewById();
        initData();
        onClickGroup();
    }

    void findViewById(){
        judulEditText = (EditText) findViewById(R.id.judulEditText);
        kategoriEditText = (EditText) findViewById(R.id.kategoriEditText);
        urlImageEditText = (EditText) findViewById(R.id.urlImageEditText);

        saveButton = (Button) findViewById(R.id.saveButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
    }

    void initData(){
        beritaViewModel = ViewModelProviders.of(this).get(BeritaViewModel.class);
        Bundle bundle = getIntent().getExtras();
        mode=bundle.getString("mode","add");
        id=bundle.getString("id","");
        judul=bundle.getString("judul","");
        kategori=bundle.getString("kategori","");
        urlImage=bundle.getString("urlImage","");
        Toast.makeText(getApplicationContext(),judul, Toast.LENGTH_LONG).show();

//        if (mode.equals("edit")){
//            getBerita(id);
//        }
    }

    void onClickGroup(){

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Berita beritaPayload = new Berita();
                beritaPayload.setJudul(judulEditText.getText().toString());
                beritaPayload.setKategori(kategoriEditText.getText().toString());
                beritaPayload.setUrlImage(urlImageEditText.getText().toString());
//                if (mode.equals("edit"))
//                    putBerita(id, beritaPayload);
//                else
                    postBerita(beritaPayload);

            }
        });
//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteBerita(id);
//                finish();
//            }
//        });
    }

    private void postBerita(Berita beritaPayload ){
        beritaViewModel.postBeritaRepository(beritaPayload).observe(this, beritaResponse -> {
//            berita = beritaResponse.getData();
            finish();
        });
    }
}