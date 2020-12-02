package com.example.tugasday4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugasday4.model.Product;
import com.example.tugasday4.model.Pulsa;
import com.example.tugasday4.viewmodels.ProductViewModel;
import com.example.tugasday4.viewmodels.PulsaViewModel;

public class PostActivity extends AppCompatActivity {

    private String id,price,nominal,code,phone_num;
    private Product product=new Product();
    private Pulsa pulsa = new Pulsa();
    //EditText phone_numberET;
    RelativeLayout postButton;
    TextView pulsaTextPopUp,priceTextPopUp;

    ProductViewModel productViewModel;
    PulsaViewModel pulsaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_product);
        findViewById();
        initData();
        pulsaTextPopUp.setText(nominal);
        priceTextPopUp.setText(price);
        onClickGroup();
    }

    void findViewById(){
        //phone_numberET = (EditText) findViewById(R.id.phone_number);
        pulsaTextPopUp = (TextView) findViewById(R.id.pulsaTextPopUp);
        priceTextPopUp = (TextView) findViewById(R.id.hargaTextView);
        postButton = (RelativeLayout) findViewById(R.id.button);
    }

    void initData(){
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        Bundle bundle = getIntent().getExtras();
        id=bundle.getString("id","");
        code=bundle.getString("code","");
        price=bundle.getString("price","");
        nominal=bundle.getString("nominal","");
        phone_num=bundle.getString("phone","");
        Toast.makeText(getApplicationContext(),nominal, Toast.LENGTH_LONG).show();
        //getProduct(id);
    }

    void onClickGroup(){

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pulsa pulsaPayload = new Pulsa();
                pulsaPayload.setCode(code);
                pulsaPayload.setPhone_number(phone_num);
                Log.d("Input", "onClick: "+phone_num);
                postPulsa(pulsaPayload);
                Toast.makeText(v.getContext(),"Berhasil",Toast.LENGTH_SHORT).show();
                Intent intent =
                        new Intent( v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void postPulsa(Pulsa pulsaPayload ){
        pulsaViewModel.postPulsaRepository(pulsaPayload).observe(this, pulsaResponse -> {
            pulsa = pulsaResponse.getData();
            finish();
        });
    }

}