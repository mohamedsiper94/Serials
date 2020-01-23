package com.example.myapplication;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegesterF extends Fragment {
    Matcher matcher;
    EditText email;

    public RegesterF() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_regester, container, false);
        Button btn_regester_fragment = view.findViewById(R.id.btn_regester_dialoge);
        btn_regester_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
                email = view.findViewById(R.id.text_email_register);

                EditText name = view.findViewById(R.id.text_name_register);
                final EditText username = view.findViewById(R.id.text_username_register);
                final EditText password = view.findViewById(R.id.text_password_register);
                final String url = "http://mohamedshehab99-001-site1.itempurl.com/regester.php";
             //  final String url="http://192.168.1.9/php_cor/regester.php";
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.equals("error the username or email is already found")) {
                            Toast.makeText(getActivity(), "error the username or email is already found", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getActivity(), "succes done register", Toast.LENGTH_SHORT).show();

                        }


                    }
                },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (email.getText().toString().isEmpty() && username.getText().toString().isEmpty() && password
                                .getText().toString().isEmpty() && email.getText().toString().isEmpty()) {
                            Toast.makeText(getActivity(), "error value not found please enter your detail", Toast.LENGTH_SHORT).show();
                        } else if (!isEmailValid(email.getText().toString())) {
                            Toast.makeText(getActivity(), "please enter valid email", Toast.LENGTH_SHORT).show();
                            email.setError("invalid email input");
                        } else if (!isusernameValid(username.getText().toString())) {
                            Toast.makeText(getActivity(), "please enter valid username", Toast.LENGTH_SHORT).show();
                            username.setError("username is invalied input please enter at lest 9 later and number");
                        }

                }
            })

            {
                @Override
                protected Map<String, String> getParams () throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                EditText name = view.findViewById(R.id.text_name_register);
                EditText username = view.findViewById(R.id.text_username_register);
                EditText password = view.findViewById(R.id.text_password_register);

                if (!name.getText().toString().isEmpty() && !username.getText().toString().isEmpty()
                        && isusernameValid(username.getText().toString()) && !password.getText().toString().isEmpty() &&
                        !email.getText().toString().isEmpty() && isEmailValid(email.getText().toString())
                ) {
                    params.put("name", name.getText().toString());
                    params.put("username", username.getText().toString().trim());
                    params.put("password", password.getText().toString().trim());
                    params.put("email", email.getText().toString().trim());
                    return params;

                } else {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
                return null;
            }


            }

            ;

                requestQueue.add(request);

        }
    });


        return view;
}

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public static boolean isusernameValid(String username) {
        boolean isValid = false;

        String expression = "^[a-z0-9_-]{6,28}$";
        CharSequence inputStr = username;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public static boolean isuserpasswordValid(String password) {
        boolean isValid = false;

        String expression = "@,$,%,&,#,";
        CharSequence inputStr = password;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }


}
