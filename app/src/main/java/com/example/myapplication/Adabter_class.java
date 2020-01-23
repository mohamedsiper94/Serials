package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adabter_class extends ArrayAdapter<Sup_mosalsal>   {
    Context context;
    int rescr;
    ArrayList<Sup_mosalsal> arrayList;
    public Adabter_class(@NonNull Context context, int resource, @NonNull ArrayList<Sup_mosalsal> objects) {
        super(context, resource, objects);
        this.context=context;
        arrayList=objects;
        rescr=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context);
        convertView=inflater.inflate(rescr,parent,false);
        Sup_mosalsal sup_mosalsal=arrayList.get(position);
        TextView textView_name=convertView.findViewById(R.id.text);
        textView_name.setText(sup_mosalsal.getName());
        ImageView imageView=convertView.findViewById(R.id.im);
        Picasso.get().load(sup_mosalsal.getUrl_img()).into(imageView);

        return convertView;
     }


}
