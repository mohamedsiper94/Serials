package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class Regester extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ProgressDialog progressDialog;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regester);
    drawerLayout=findViewById(R.id.xx);
    toolbar=findViewById(R.id.toolbar);
    navigationView=findViewById(R.id.her);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    toggle=new ActionBarDrawerToggle(Regester.this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
    drawerLayout.addDrawerListener(toggle);
    toggle.syncState();
    navigationView.setNavigationItemSelectedListener(this);
        Button btn_login=findViewById(R.id.Login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.vew,new loginF());
                transaction.commit();

            }
        });
       Button btn_regester=findViewById(R.id.Regester);
       btn_regester.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               FragmentManager fragmentManager=getSupportFragmentManager();
               FragmentTransaction transaction=fragmentManager.beginTransaction();
               transaction.replace(R.id.vew,new RegesterF());
               transaction.commit();

           }
       });


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.favorit_bage:
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.vew,new favorit());
                transaction.commit();


           break;
            case  R.id.rate:
Intent intent=new Intent(Regester.this,MainActivity.class);
startActivity(intent);
break;

        }

        return false;
    }

    }

