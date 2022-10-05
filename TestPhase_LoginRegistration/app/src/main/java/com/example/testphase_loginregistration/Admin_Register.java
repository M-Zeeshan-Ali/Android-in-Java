package com.example.testphase_loginregistration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.app.DownloadManager;
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

public class Admin_Register extends AppCompatActivity {
        AppCompatEditText name,lastname,email,password,contact;
        AppCompatButton register;
        RadioButton radio1,radio2;
        RadioGroup gender;
        String getgender;

        String emailPattern ="[a-zA-Z][a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_register);
        name= findViewById(R.id.name);
        lastname= findViewById(R.id.lname);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        contact=findViewById(R.id.contact);

        register=findViewById(R.id.register);

        radio1=findViewById(R.id.radio1);
        radio2=findViewById(R.id.radio2);

    register.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String na = name.getText().toString();
            String lna = lastname.getText().toString();
            String em = email.getText().toString();
            String pas = password.getText().toString();
            String con = contact.getText().toString();

            if (TextUtils.isEmpty(na)) {
                name.setError("please enter the name");
                name.requestFocus();
                return;
            } else if (TextUtils.isEmpty(lna)) {
                lastname.setError("please enter the last name");
                lastname.requestFocus();
                return;
            } else if (TextUtils.isEmpty(em) && !em.matches(emailPattern)) {
                email.setError("please enter the valid email");
                email.requestFocus();
                return;
            } else if (TextUtils.isEmpty(pas)) {
                password.setError("please enter the valid password");
                password.requestFocus();
                return;
            } else if (TextUtils.isEmpty(con)) {
                contact.setError("Enter the Contact number");
                contact.requestFocus();
                return;
            } else {

                if (radio1.isChecked()) {
                    getgender = radio1.getText().toString();
                } else {
                    getgender = radio2.getText().toString();
                }

                //adminname(ad) changing later
                Log.d("", "" + linkapi.url + "admin_register.php?name=" + na + "&lastname=" + lna +
                        "&email=" + em + "&password=" + pas + "&contact=" + con +
                        "&gender=" + getgender + "");

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.GET, //api name is also Admin_Register.php & Java file.
                        linkapi.url + "admin_register.php?name=" + na + "&lastname=" + lna +
                                "&email=" + em + "&password=" + pas + "&contact=" + con +
                                "&gender=" + getgender + "",
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String res = response.getString("success");
                                    if (res.equals("true")) {
                                        //AlerDialog is class and builder is a nested class of alertdialog ,object is created
                                        AlertDialog.Builder builder = new AlertDialog.Builder(Admin_Register.this);
                                        builder.setTitle("success");    // nested class of alertdialog for set the message
                                        builder.setNegativeButton("Ok", null);
                                        builder.create();
                                        builder.show();

                                        // intent object is created for move admin_register (current activtiy) to next activity(admin_login)
                                        Intent intent = new Intent(Admin_Register.this,Admin_Login.class);
                                        startActivity(intent);  //start the activity method is call(varibale intent parameter)

                                    } else if (res.equals("false")) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(Admin_Register.this);
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
                        }


                );
                Volley.newRequestQueue(getApplicationContext()).add(jsonObjectRequest);

                //  });
            }
        }
    }
    );}
}

