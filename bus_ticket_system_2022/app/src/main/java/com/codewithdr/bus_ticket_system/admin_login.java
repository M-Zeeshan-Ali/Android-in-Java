package com.codewithdr.bus_ticket_system;

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
//AppCompatActivity is superClass ,extends keyword is used to inherit the admin-login base class or child class
public class admin_login extends AppCompatActivity {
    AppCompatButton login;
    AppCompatTextView register;
    AppCompatEditText email, password;      //adim_    email,password and login ****
    static String passemail = "";

        //standard pattern of email is initialize using string variable name(emailpattern).
    String emailPattern = "[a-zA-Z][a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override       //when our activity is start first onCreate method is call (android lifecycle method is created)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);     // for calling the superclass of constructor.
        setContentView(R.layout.activity_admin_login);//view the activity of admin_login of our UI layout.
        //findViewById is a function to get View
        // (R means Resourse Folder .id is a reference of id call .login is a xml id name)
        login = findViewById(R.id.login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register=findViewById(R.id.register);
        //setonClickListener method is used  on register button.
        register.setOnClickListener(new View.OnClickListener() {
            @Override           //
            public void onClick(View v) {
    //intent object is created for moving the current activity to next activity(notAccount Register First ).
                Intent intent=new Intent(admin_login.this,admin_register.class);
                startActivity(intent);  //start the activity(variable name is intent )
            }
        });
//setonClickListener is class of click listener for click the function
        login.setOnClickListener(new View.OnClickListener() {
            @Override       //onclickFunction (parameter pass  view is variable declare the v)
            public void onClick(View v) {
/*em is a variable name of String type and initialize  the email(.reference pass of email) getText() is a method to convert the
text into String method.        gettext() is a method to return the string value
   while settext is a method to receive the string value.*/
                String em = email.getText().toString();
                String pass = password.getText().toString();
                    //nested if_else condition is applied on email,password
                if (TextUtils.isEmpty(em) && !em.matches(emailPattern)) {

                    email.setError("Please enter the valid email");
                    email.requestFocus();       //
                    return;

                } else if (TextUtils.isEmpty(pass)) {

                    password.setError("Please enter the password");
                    password.requestFocus();
                    return;
                } else {
                    //log.d is a method to debug (and show) the message on logcat.
                    Log.d("", "" + linkapi.url + "admin_login.php?email=" + em + "&password=" + pass + "");
                    //Json is a class ,Json object is created to provide communication of request response path
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, linkapi.url + "admin_login.php?email=" + em + "&password=" + pass + "",
                            null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
        /*in log.d try-catch mehtod is used ,within try
        try to execute the code if executed continue other wise it will terminate
        and go to the catch side*/
                                    try {
                                        Log.d("Passemail", "" + passemail);

                                        String res = response.getString("success");
                                        if (res.equals("true")) {
                                            passemail = email.getText().toString();
//AlertDialog is parent_class ,Builder is a child Class (nested class of AlertDialog) object is created for set the short message of current admin_login
                                            AlertDialog.Builder builder = new AlertDialog.Builder(admin_login.this);
                                            builder.setTitle("success");    //settitle method is used on builder_variable.
                                            builder.setNegativeButton("Ok", null);
                                            builder.create();
                                            builder.show();
        //this intent object is used to move admin_login activity to next admin_dashboard activity.
                                            Intent intent = new Intent(admin_login.this, admin_dashboard.class);
                                            startActivity(intent);

                                        } else if (res.equals("false")) {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(admin_login.this);
                                            builder.setTitle("Error");
                                            builder.setNegativeButton("Try Again", null);
                                            builder.create();
                                            builder.show();


                                        }
                      //catch(exception  ex) //error handling is also called exception handling
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
                    //volley library is used for communication between api and android app.
                    Volley.newRequestQueue(admin_login.this).add(jsonObjectRequest);
                }
            }
        });
    }
}