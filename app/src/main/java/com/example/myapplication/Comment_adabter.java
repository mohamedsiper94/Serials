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

public class Comment_adabter extends ArrayAdapter<Comment_cla> {
Context context;
int resorce;
ArrayList<Comment_cla> arrayList;
    public Comment_adabter(@NonNull Context context, int resource, @NonNull ArrayList<Comment_cla> objects) {
        super(context, resource, objects);
        this.context=context;
        resorce=resource;
        arrayList=objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context);
        convertView=inflater.inflate(resorce,parent,false);
          Comment_cla comment_cla=arrayList.get(position);
        TextView textView=convertView.findViewById(R.id.autoCompleteTextView);
        textView.setText(comment_cla.getComment());
        TextView textView1=convertView.findViewById(R.id.tex_usernae);
        textView1.setText(  comment_cla.getUsername());
        TextView textView2=convertView.findViewById(R.id.time);
        textView2.setText(  comment_cla.getTime());
return convertView;


    }
}
