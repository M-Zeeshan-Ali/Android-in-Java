package com.codewithdr.bus_ticket_system;       //package name of my project
// package import or java libraries files
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
//make a class on publicaly to access public ,class name admin_login
public class admin_login extends AppCompatActivity {
    AppCompatButton login;          //variable declare login button call
    AppCompatTextView register;     // register button declare,variable name is register
    AppCompatEditText email, password;  // email,and passoword variable is declared in case only edittext
    static String passemail = "";       //specific standard passing the e_mail

//string variable name is "emailpattern" assign a unique email pattern ,not used for empty email
    String emailPattern = "[a-zA-Z][a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
//data binding
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);      //contentview on login activity of admin user
        login = findViewById(R.id.login);          //login variable assign w.r.t id name "login" by xml design
        email = findViewById(R.id.email);      //email variable is assign w.r.t id name "email" from xml design
        password = findViewById(R.id.password); //password variable is assign w.r.t id name "password"

        /* when login button is pressed ,an click sound created "login.setOnClickListener(new View.OnClickListener()"*/

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String em = email.getText().toString(); // string variable "email" to get the text from string variable
                String pass = password.getText().toString();    //string variable of password to get text from string variable

                /*decision condition ,if empty email and email pattern is not match w.r.t to unique email then message shows
                /or setError "please enter the valid email" */
                if (TextUtils.isEmpty(em) && !em.matches(emailPattern)) {

                    email.setError("Please enter the valid email");
                    email.requestFocus();
                    return;                 //and return back to check either uniqe id or not
                    //if password text is empty or not unique then "message show plz enter the password"
                } else if (TextUtils.isEmpty(pass)) {

                    password.setError("Please enter the password");
                    password.requestFocus();
                    return;
                } else
                    //otherwise show debugger or view any error on android bottom side and our emulator.
                    {

                    Log.d("", "" + linkapi.url + "admin_login.php?email=" + em + "&password=" + pass + "");

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, linkapi.url + "admin_login.php?email=" + em + "&password=" + pass + "",
                            null,
                            new Response.Listener<JSONObject>() {

                                                /* data binding */
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        Log.d("Passemail", "" + passemail);

                                        String res = response.getString("success");
                                        if (res.equals("true")) {
                                            passemail = email.getText().toString();

                                            AlertDialog.Builder builder = new AlertDialog.Builder(admin_login.this);
                                            builder.setTitle("success");
                                            builder.setNegativeButton("Ok", null);
                                            builder.create();
                                            builder.show();
                                            Intent intent = new Intent(admin_login.this, admin_dashboard.class);
                                            startActivity(intent);

                                        } else if (res.equals("false")) {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(admin_login.this);
                                            builder.setTitle("Error");
                                            builder.setNegativeButton("Try Again", null);
                                            builder.create();
                                            builder.show();


                                        }


                                    } catch (Exception ex) {
                                        Log.d("", "" + ex);
                                    }

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                                }
                            });
                    //volley lirbrary is used to provide communication path between database and emulator.
                    Volley.newRequestQueue(admin_login.this).add(jsonObjectRequest);
                }
            }
        });
    }
}