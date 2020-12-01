package com.example.tugasday3.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tugasday3.R;
import com.example.tugasday3.ViewActivity;
import com.example.tugasday3.model.Berita;

import java.util.ArrayList;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder> {

    Context context;
    ArrayList<Berita> beritas;

    public BeritaAdapter(Context context, ArrayList<Berita> beritas) {
        this.context = context;
        this.beritas = beritas;
    }

    @NonNull
    @Override
    public BeritaAdapter.BeritaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_berita, parent, false);
        return new BeritaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeritaAdapter.BeritaViewHolder holder, int position) {
        holder.judulTv.setText(beritas.get(position).getJudul());
        holder.kategoriTv.setText(beritas.get(position).getKategori());
//        holder.urlImageIv.setText(beritas.get(position).getUrlImage());
        Glide.with(context).load(beritas.get(position).getUrlImage()).into(holder.urlImageIv);

        holder.beritaLl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),beritas.get(position).getJudul(),Toast.LENGTH_SHORT).show();
                Intent intent =
                        new Intent( context, ViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("mode", "edit");
                bundle.putString("id", beritas.get(position).getId().toString());
                bundle.putString("judul", beritas.get(position).getJudul());
                bundle.putString("kategori", beritas.get(position).getKategori());
                bundle.putString("urlImage", beritas.get(position).getUrlImage());
                intent.putExtras(bundle);
                context.startActivity(intent);
            };
        });
    }

    @Override
    public int getItemCount() {
        return beritas.size();
    }

    public class BeritaViewHolder extends RecyclerView.ViewHolder {
        TextView judulTv;
        TextView kategoriTv;
        ImageView urlImageIv;
        LinearLayout beritaLl;

        public BeritaViewHolder(@NonNull View itemView) {
            super(itemView);

            judulTv = itemView.findViewById(R.id.judulTextView);
            kategoriTv = itemView.findViewById(R.id.kategoriTextView);
            urlImageIv = itemView.findViewById(R.id.urlImageImageView);
            beritaLl = itemView.findViewById(R.id.beritaLl);
        }
    }
}
