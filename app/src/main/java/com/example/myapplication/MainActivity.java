package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {
   private RequestQueue mQ,mqq; int code;  GridView listView;
   String name1;  Sup_mosalsal sup_mosalsal;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<Sup_mosalsal>  arrayList=new ArrayList< >();



        listView=findViewById(R.id.lis);
        mQ = Volley.newRequestQueue(this);

      String url ="http://mohamedshehab99-001-site1.itempurl.com/json.php";
       // final String url="http://192.168.1.9/php_cor/json.php";

        final JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    final JSONArray jsonArray =response.getJSONArray("data");
                for (int i=0;i<jsonArray.length();i++){
                     final JSONObject sub_mosasal=jsonArray.getJSONObject(i);
                         name1=sub_mosasal.getString("name");
                         int code=sub_mosasal.getInt("code");
                         String url=sub_mosasal.getString("image");
                           sup_mosalsal=new Sup_mosalsal(name1,code,url);
                         arrayList.add(sup_mosalsal);

                }
            Adabter_class adabter_class=new Adabter_class(MainActivity.this,R.layout.liv,arrayList);
                listView.setAdapter(adabter_class);
                registerForContextMenu(listView);
               listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                              Sup_mosalsal sup_mosalsal2=new Sup_mosalsal();
                              sup_mosalsal2=arrayList.get(position);

                                     String username=getIntent().getStringExtra("username");

                           Intent intent1=new Intent(MainActivity.this,Main2Activity.class);
                        intent1.putExtra("data",sup_mosalsal2.getCode());
                        intent1.putExtra("data1",sup_mosalsal2.getCode());
                        intent1.putExtra("username1" ,username );
                        intent1.putExtra("name",sup_mosalsal2.getName());
                       startActivity(intent1);



                    }
                });



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQ.add(request);
                registerForContextMenu(listView);
     }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.favorite_menu,menu);


        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int id= item.getItemId();
         if(id== R.id.vo){
           listView.setSelection(info.position);
           Intent intent=new Intent(MainActivity.this,favorit.class);
           intent.putExtra("data",listView.getSelectedItemId());
           startActivity(intent);

         }



        return super.onContextItemSelected(item);
    }
}
