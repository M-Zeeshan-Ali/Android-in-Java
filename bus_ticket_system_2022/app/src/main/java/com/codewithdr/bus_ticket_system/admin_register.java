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
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
//appcompatActivity is a parent class ,inherited of child class using extend keyword
public class admin_register extends AppCompatActivity {
   //userID, firstName, lastName, (address or email), contact_No, and gender
    //admin Registration form
    AppCompatEditText name,lastname,email,password,phoneno; // variable are declared from appcompatEditText
    AppCompatButton register;       //register variable is declared through appcompatbutton

    RadioButton radio1,radio2;      //Radio button is declared radio1 and radio2.
    RadioGroup gender;              //gender variable is declared through RadioGroup class.
    String getgender;               //string variable is declared getgender.

    String emailPattern = "[a-zA-Z][a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";   //emalilpattern is initialize through String variable ,variable name is emailpattern
    //data binding
    //when our /activity/program/ is created then onCreate method is call //android activity lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); /*super is a keyword for calling the super/parent class constructor
        .oncreate is the method to access the properties.*/
        setContentView(R.layout.activity_admin_register);   //setContentView is a method to set the xml layout on our activity_admin_register
        name = findViewById(R.id.name);             //name variable is initialized getting view we use findviewbyId function
        lastname = findViewById(R.id.lname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phoneno = findViewById(R.id.phone);
        register = findViewById(R.id.register);

        radio1=findViewById(R.id.radio1);
        radio2=findViewById(R.id.radio2);
//setonclicklistener is method for using click attribute on register button.

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String na = name.getText().toString();          //
                String lna = lastname.getText().toString();     //      .get text is a method return the string,.set method receive a string.
                String em = email.getText().toString();
                String pass = password.getText().toString();
                String phone = phoneno.getText().toString();
                                                                //if else condition , nested ifelse
                if (TextUtils.isEmpty(na)) {

                    name.setError("Please enter the name");             //if name edit text is empty ,set the error
                    name.requestFocus();                //requestFocus()  //to focus on  component
                    return;
                } else if (TextUtils.isEmpty(lna)) {

                    lastname.setError("Please enter the lastname");
                    lastname.requestFocus();                            //requestFocus()  //to focus on  component
                    return;

                } else if (TextUtils.isEmpty(em) && !em.matches(emailPattern)) {

                    email.setError("Please enter the valid email");
                    email.requestFocus();
                    return;                         // finish the execuation of method.

                } else if (TextUtils.isEmpty(pass)) {

                    password.setError("Please enter the password");
                    password.requestFocus();
                    return;
                } else if (TextUtils.isEmpty(phone)) {
                    phoneno.setError("Please enter the phone");
                    phoneno.requestFocus();
                    return;
                } else {

                    if(radio1.isChecked())
                    {
                        getgender=radio1.getText().toString();
                    }
                    else
                    {
                        getgender=radio2.getText().toString();
                    }
                        //log.d is a method used to debug the message //for logcat display the message
                    Log.d("", "" + linkapi.url + "admin_register.php?name=" + na + "&lastname=" + lna + "&aemail=" + em + "&apassword=" + pass + "&adphone=" + phone +
                            "&gender=" + getgender + "");
                    //JSon is a object for request and response communication.
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                            Request.Method.GET,
                            linkapi.url + "admin_register.php?name=" + na + "&lastname=" + lna + "&aemail=" + em + "&apassword=" + pass + "&adphone=" + phone +
                                    "&gender=" + getgender + "",
                            null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        String res = response.getString("success");
                                        if (res.equals("true")) {
                                            //AlerDialog is class and builder is a nested class of alertdialog ,object is created
                                            AlertDialog.Builder builder = new AlertDialog.Builder(admin_register.this);
                                            builder.setTitle("success");    // nested class of alertdialog for set the message
                                            builder.setNegativeButton("Ok", null);
                                            builder.create();
                                            builder.show();
                                            // intent object is created for move admin_register (current activtiy) to next activity(admin_login)
                                            Intent intent = new Intent(admin_register.this, admin_login.class);
                                            startActivity(intent);  //start the activity method is call(varibale intent parameter)

                                        } else if (res.equals("false")) {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(admin_register.this);
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
                    );          //volley library is path , used for communication between database connectivity
                    Volley.newRequestQueue(getApplicationContext()).add(jsonObjectRequest);

                }

            }
        //}}
        });

    }
}
