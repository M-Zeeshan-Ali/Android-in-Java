package com.codewithdr.bus_ticket_system;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

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

public class register_user extends AppCompatActivity {

    AppCompatEditText name,email,password,cnic,phoneno;
    AppCompatButton register;

    String emailPattern = "[a-zA-Z][a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        cnic=findViewById(R.id.cnic);
        phoneno=findViewById(R.id.phone);
        register=findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String na=name.getText().toString();
                String em=email.getText().toString();
                String pass=password.getText().toString();
                String cni=cnic.getText().toString();
                String phone=phoneno.getText().toString();
                if(TextUtils.isEmpty(na))
                {

                    name.setError("Please enter the name");
                    name.requestFocus();
                    return;
                }
                else if(TextUtils.isEmpty(em) && !em.matches(emailPattern))
                {

                    email.setError("Please enter the valid email");
                    email.requestFocus();
                    return;

                }

                else if(TextUtils.isEmpty(pass))
                {

                    password.setError("Please enter the password");
                    password.requestFocus();
                    return;
                }

                else if(TextUtils.isEmpty(cni))
                {
                    cnic.setError("Please enter the CNIC");
                    cnic.requestFocus();
                    return;
                }
                else if(TextUtils.isEmpty(phone))
                {
                    phoneno.setError("Please enter the password");
                    phoneno.requestFocus();
                    return;
                }
                else {

                    Log.d("",""+linkapi.url+"user_register.php?name="+na+"&email="+em+"&password="+pass+"&phone="+phone+"&cnic="+cni+"");
                    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                            Request.Method.GET,
                            linkapi.url+"user_register.php?name="+na+"&email="+em+"&password="+pass+"&phone="+phone+"&cnic="+cni+"",
                            null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        String res=response.getString("success");
                                        if(res.equals("true"))
                                        {

                                            AlertDialog.Builder builder=new AlertDialog.Builder(register_user.this);
                                            builder.setTitle("success");
                                            builder.setNegativeButton("Ok",null);
                                            builder.create();
                                            builder.show();
                                            Intent intent=new Intent(register_user.this,user_login.class);
                                            startActivity(intent);

                                        }
                                        else if(res.equals("false"))
                                        {
                                            AlertDialog.Builder builder=new AlertDialog.Builder(register_user.this);
                                            builder.setTitle("Error");
                                            builder.setNegativeButton("Try Again",null);
                                            builder.create();
                                            builder.show();


                                        }


                                    }
                                    catch (Exception ex)
                                    {
                                        Log.d("Register",""+ex);
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                                }
                            }
                    );
                    Volley.newRequestQueue(getApplicationContext()).add(jsonObjectRequest);


                }
            }
        });

    }
}