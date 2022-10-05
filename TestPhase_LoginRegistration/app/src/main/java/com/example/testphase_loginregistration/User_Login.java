package com.example.testphase_loginregistration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class User_Login extends AppCompatActivity {

    AppCompatEditText email,password;
    AppCompatButton login;

    AppCompatTextView register;
    static String passemail="";

    String emailPattern="[a-zA-Z][a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);

        register=findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(User_Login.this,User_Register.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em = email.getText().toString();
                String pass = password.getText().toString();

                if (TextUtils.isEmpty(em) && !em.matches(emailPattern)) {
                    email.setError("Enter the Valid Email");
                    email.requestFocus();
                    return;

                } else if (TextUtils.isEmpty(pass)) {
                    password.setError("enter the Valid Password");
                    password.requestFocus();
                    return;
                } else {
                    Log.d("", "" + linkapi.url + "user_login.php?email=" + em +
                            "&password=" + pass + "");

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method
                            .GET, linkapi.url + "admin_login.php?email=" + em +
                            "&password=" + pass + "", null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {

                                    try {
                                        Log.d("passemail", "" + passemail);

                                        String res = response.getString("success");

                                        if (res.equals("true")) {
                                            passemail = email.getText().toString();

                                            AlertDialog.Builder builder = new AlertDialog.Builder(User_Login.this);
                                            builder.setTitle("success");
                                            builder.setNegativeButton("Ok", null);
                                            builder.create();
                                            builder.show();
                                        } else if (res.equals("false")) {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(User_Login.this);
                                            builder.setTitle("Error! Try Again");
                                            builder.setNegativeButton("Try Again", null);
                                            builder.create();
                                            builder.show();
                                        }


                                    } catch (Exception ex) {
                                        Log.d("", "" + ex);
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
                    Volley.newRequestQueue(User_Login.this).add(jsonObjectRequest);
                }
            }

        });


    }
}