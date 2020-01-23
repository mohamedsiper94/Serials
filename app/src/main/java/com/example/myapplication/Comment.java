package com.example.myapplication;


import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Comment extends Fragment {
    Bundle bundle;
    int code;
    EditText editText;


    View view;

    String username;

    public Comment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_comment2, container, false);
        final ListView listView = view.findViewById(R.id.lis_comment);
        editText = view.findViewById(R.id.text_comment);
        final ArrayList<Comment_cla> arrayList = new ArrayList<>();

        String url1 = "http://mohamedshehab99-001-site1.itempurl.com/show_comments.php";
        final RequestQueue mqqq = Volley.newRequestQueue(view.getContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url1, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data1");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject comment = jsonArray.getJSONObject(i);
                        final String comment1 = comment.getString("comment");
                        final int code1 = comment.getInt("code_person");
                        final String username1 = comment.getString("username");
                        final String time = comment.getString("data");
                        bundle = Comment.this.getArguments();
                        code = bundle.getInt("data1");


                        if (code == code1) {
                            Comment_cla comment_cla = new Comment_cla(username1, comment1, code1, time);
                            arrayList.add(comment_cla);

                        }
                        final Comment_adabter comment_adabter = new Comment_adabter(getActivity(), R.layout.viewcommen, arrayList);
                        listView.setAdapter(comment_adabter);
                        comment_adabter.notifyDataSetInvalidated();
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


        mqqq.add(request);

        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swip);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        final ListView listView = view.findViewById(R.id.lis_comment);
                        editText = view.findViewById(R.id.text_comment);
                        final ArrayList<Comment_cla> arrayList = new ArrayList<>();

                        String url1 = "http://mohamedshehab99-001-site1.itempurl.com/show_comments.php";
                     //   final String url1="http://192.168.1.9/php_cor/show_comments.php";

                        final RequestQueue mqqq = Volley.newRequestQueue(view.getContext());
                        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url1, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray jsonArray = response.getJSONArray("data1");
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject comment = jsonArray.getJSONObject(i);
                                        final String comment1 = comment.getString("comment");
                                        final int code1 = comment.getInt("code_person");
                                        final String username1 = comment.getString("username");
                                        final String time = comment.getString("data");
                                        bundle = Comment.this.getArguments();
                                        code = bundle.getInt("data1");


                                        if (code == code1) {
                                            Comment_cla comment_cla = new Comment_cla(username1, comment1, code1, time);
                                            arrayList.add(comment_cla);

                                        }
                                        final Comment_adabter comment_adabter = new Comment_adabter(getActivity(), R.layout.viewcommen, arrayList);
                                        listView.setAdapter(comment_adabter);
                                        comment_adabter.notifyDataSetInvalidated();
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


                        mqqq.add(request);
                    }
                }, 3200);
            }
        });


        doTheAutoRefresh();


        return view;
    }

    private void doTheAutoRefresh() {


        Button button = view.findViewById(R.id.btn_send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {


               String url = "http://mohamedshehab99-001-site1.itempurl.com/comments.php";
            //   final String url="http://192.168.1.9/php_cor/comments.php";

                RequestQueue mq = Volley.newRequestQueue(view.getContext());
                StringRequest request2 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

                    public void onResponse(String response) {
                        if (response.equals("error")) {
                            Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                        } else {


                            Toast.makeText(getActivity(), "done send comment", Toast.LENGTH_SHORT).show();
                            editText.getText().clear();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (editText.getText().toString().isEmpty()) {
                            Toast.makeText(getActivity(), "Error please enter comment", Toast.LENGTH_SHORT).show();
                        } else if (username == null) {
                            Toast.makeText(getActivity(), "please regester", Toast.LENGTH_SHORT).show();
                        }
                    }
                }) {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parames = new HashMap<>();
                        bundle = Comment.this.getArguments();
                        code = bundle.getInt("data1");
                        username = bundle.getString("username");
                        Log.d("json", "id is :" + username);
                        if (!editText.getText().toString().isEmpty() && username != null) {
                            parames.put("username", username.trim().toString());
                            parames.put("code_person", String.valueOf(code));
                            parames.put("comment", editText.getText().toString());


                        } else {

                            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();

                        }

                        return parames;
                    }
                };
                mq.add(request2);


            }

        });


    }

}
