package com.example.tugasday2.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tugasday2.R;
import com.example.tugasday2.model.Berita;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class BeritaAdapter extends BaseAdapter {

    Context context;
    private List<Berita> list;

    public BeritaAdapter(Context context, List<Berita> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressWarnings("deprecation")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(R.layout.item_berita, null);
        }
        Berita berita = list.get(position);
        TextView judulTextView = (TextView) convertView.findViewById(R.id.judulTextView);
        TextView kategoriTextView = (TextView) convertView.findViewById(R.id.kategoriTextView);
        ImageView urlImageView = (ImageView) convertView.findViewById(R.id.urlImageView);

        judulTextView.setText(berita.getJudul());
        kategoriTextView.setText(berita.getKategori());
//        urlImageView.setImageURI(Uri.parse(berita.getUrlImage()));
        new uploadImage(urlImageView).execute(berita.getUrlImage());

        return convertView;
    }

    private class uploadImage extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public uploadImage(ImageView imageView) {
            this.imageView = imageView;
        }
        @Override
        protected Bitmap doInBackground(String... url) {
            String urlString = url[0];
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new java.net.URL(urlString).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }
}
