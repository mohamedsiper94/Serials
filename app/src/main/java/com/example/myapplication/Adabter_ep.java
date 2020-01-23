package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Adabter_ep extends ArrayAdapter<Epson> {
    Context context;
    int resoorse;
    ArrayList<Epson> arrayList;
    public Adabter_ep(@NonNull Context context, int resource, @NonNull ArrayList<Epson> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resoorse=resource;
        this.arrayList=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context);
        convertView = inflater.inflate(resoorse,parent,false);
        Epson epson=arrayList.get(position);
        TextView textView_ep=convertView.findViewById(R.id.text_ep);
        textView_ep.setText(epson.getName());

        return convertView;
    }
}
