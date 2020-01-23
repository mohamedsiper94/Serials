package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class loginF extends Fragment {
    EditText username1;

    public loginF() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_login, container, false);
         view.findViewById(R.id.btn_enter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                username1=view.findViewById(R.id.text_username_login);
                RequestQueue mqqq= Volley.newRequestQueue(v.getContext());
               String url1="http://mohamedshehab99-001-site1.itempurl.com/show_users.php";
              // final String url1="http://192.168.1.9/php_cor/show_users.php";

                StringRequest request=new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("Succes")){
                            Intent intent=new Intent(v.getContext(),MainActivity.class);
                            intent.putExtra("username",username1.getText().toString());
                            startActivity(intent);
                            Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(getActivity(), "incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "error" + error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String ,String> parmss=new HashMap<>();

                        EditText password=view.findViewById(R.id.text_password_login);
                        parmss.put("username",username1.getText().toString().trim());
                        parmss.put("password",password.getText().toString().trim());
                        return parmss;
                    }

                };
                mqqq.add(request);
            }
        });
        return view;
    }


}
