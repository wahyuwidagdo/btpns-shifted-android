package com.example.tugasday4.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugasday4.PostActivity;
import com.example.tugasday4.R;
import com.example.tugasday4.model.Product;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    Context context;
    ArrayList<Product> products;
    String phone_numberET;

    public ProductAdapter(Context context, ArrayList<Product> products, String phone_numberET) {
        this.context = context;
        this.products = products;
        this.phone_numberET = phone_numberET;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pulsa, parent, false);
        return new  ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.nominalTextView.setText(String.valueOf(products.get(position).getNominal()));
        Log.d("nominal", "onBindViewHolder: "+String.valueOf(products.get(position).getNominal()));holder.productRl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),products.get(position).getNominal().toString(),Toast.LENGTH_SHORT).show();
                Intent intent =
                        new Intent( context, PostActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("mode", "edit");
                bundle.putString("id", products.get(position).getId().toString());
                bundle.putString("code", products.get(position).getCode());
                bundle.putString("price", products.get(position).getPrice().toString());
                bundle.putString("nominal", products.get(position).getNominal().toString());
                bundle.putString("phone", phone_numberET);
                intent.putExtras(bundle);
                context.startActivity(intent);
            };
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.nominalTextView) TextView nominalTextView;
        @BindView(R.id.nominalRl) RelativeLayout productRl;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
