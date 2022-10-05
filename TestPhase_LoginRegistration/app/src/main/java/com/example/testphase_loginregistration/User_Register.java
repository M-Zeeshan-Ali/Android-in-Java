package com.example.testphase_loginregistration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class User_Register extends AppCompatActivity {
    AppCompatEditText name,lastname,email,password,contact,cnic;
    AppCompatButton register;

    RadioButton radio1,radio2;      //Radio button is declared radio1 and radio2.
    RadioGroup gender;              //gender variable is declared through RadioGroup class.
    String getgender;

    String emailPattern = "[a-zA-Z][a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        name = findViewById(R.id.name);
        lastname = findViewById(R.id.lname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        cnic = findViewById(R.id.cnic);
        contact = findViewById(R.id.phone);
        register = findViewById(R.id.register);


        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String na = name.getText().toString();
                String lna = lastname.getText().toString();
                String em = email.getText().toString();
                String pass = password.getText().toString();
                String cni = cnic.getText().toString();
                String con = contact.getText().toString();

                if (TextUtils.isEmpty(na)) {

                    name.setError("Please enter the name");
                    name.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(lna)) {

                    name.setError("Please enter the  lastname");
                    name.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(em) && !em.matches(emailPattern)) {

                    email.setError("Please enter the valid email");
                    email.requestFocus();
                    return;

                } else if (TextUtils.isEmpty(pass)) {

                    password.setError("Please enter the password");
                    password.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(cni)) {
                    cnic.setError("Please enter the CNIC");
                    cnic.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(con)) {
                    contact.setError("Please enter the phone");
                    contact.requestFocus();
                    return;
                } else {

                    if (radio1.isChecked()) {
                        getgender = radio1.getText().toString();
                    } else {
                        getgender = radio2.getText().toString();
                    }
                }
                Log.d("", "" + linkapi.url + "user_register.php?name=" + na + "&lastname=" + lna + "&email=" + em + "&password=" + pass + "&cnic=" + cni + "&contact=" + con + "&gender=" + getgender + "");
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.GET,
                        linkapi.url + "user_register.php?name=" + na + "&lastname=" + lna + "&email=" + em + "&password=" + pass + "&cnic=" + cni + "&contact=" + con + "&gender=" + getgender + ""
                        , null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String res = response.getString("success");

                                    if (res.equals("true")) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(User_Register.this);
                                        builder.setTitle("success,Zeeshan VeryGood");
                                        builder.setNegativeButton("OK", null);
                                        builder.create();
                                        builder.show();

                                        Intent intent = new Intent(User_Register.this, User_Login.class);
                                        startActivity(intent);
                                    } else if (res.equals("false")) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(User_Register.this);
                                        builder.setTitle("Error");
                                        builder.setNegativeButton("Try Again", null);
                                        builder.create();
                                        builder.show();
                                    }


                                } catch (Exception ex) {
                                    Log.d("Register", "" + ex);
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }

                        });

                Volley.newRequestQueue(getApplicationContext()).add(jsonObjectRequest);
            }

        });
    }
}