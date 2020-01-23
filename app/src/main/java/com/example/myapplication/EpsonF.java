package com.example.myapplication;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class EpsonF extends Fragment {
private Bundle bundle;
int code1;
    public EpsonF() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
           View view=inflater.inflate(R.layout.fragment_epson2, container, false);
       //   final int f=getArguments().getInt("data");

             request(view);

        return view ;
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void request(View view){

         final ArrayList<Epson> arrayList=new ArrayList<>();
        final ListView listView=view.findViewById(R.id.list_epson);


        RequestQueue mq = Volley.newRequestQueue(view.getContext());
        String url ="http://mohamedshehab99-001-site1.itempurl.com/jason_ep.php";
      //  final String url="http://192.168.1.9/php_cor/jason_ep.php";

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("data1");
                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject epson5 = jsonArray.getJSONObject(i);
                        String name = epson5.getString("name_of_ep");
                        int number = epson5.getInt("number");
                        String url = epson5.getString("url");
                        int code = epson5.getInt("code1");
                        Bundle  bundle;

                            bundle=EpsonF.this.getArguments();
                            code1=bundle.getInt("data");
                              if (code1 == code) {


                                 Epson epson = new Epson(name, number, url, code);
                                 arrayList.add(epson);


                         }



                        Adabter_ep adabter_ep = new Adabter_ep(getActivity(), R.layout.view_epson, arrayList);
                        listView.setAdapter(adabter_ep);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Epson epson1 = new Epson();
                                epson1 = arrayList.get(position);
                                 Intent intent = new Intent(view.getContext(),Main3Activity.class);
                                intent.putExtra("url",epson1.getUrl());
                                startActivity(intent);

                            }
                        });

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mq.add(request);

    }



}
