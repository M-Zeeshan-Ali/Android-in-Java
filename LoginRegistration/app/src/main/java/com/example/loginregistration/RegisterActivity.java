package com.example.loginregistration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputEditText;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    //MaterialEditText

    MaterialEditText userName,email,password,mobile;
    RadioGroup radioGroup;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        userName= findViewById(R.id.username);
        email= findViewById(R.id.email);
        password= findViewById(R.id.password);
        mobile= findViewById(R.id.mobile);

        radioGroup=(RadioGroup)findViewById(R.id.radiogp);
        register=findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String txtUserName = userName.getText().toString();
                String txtEmail = email.getText().toString();
                String txtPassword =password.getText().toString();
                String txtMobile = mobile.getText().toString();

                if (TextUtils.isEmpty(txtUserName) ||
                        TextUtils.isEmpty(txtEmail) ||
                        TextUtils.isEmpty(txtPassword) ||
                        TextUtils.isEmpty(txtMobile)) {
                    Toast.makeText(RegisterActivity.this, "All field Required", Toast.LENGTH_SHORT).show();

                } else {
                    int genderId = radioGroup.getCheckedRadioButtonId();
                    RadioButton selected_Gender = radioGroup.findViewById(genderId);
                    if (selected_Gender == null) {
                        Toast.makeText(RegisterActivity.this, "selected gender please", Toast.LENGTH_SHORT).show();
                        else{
                            String selectGender = selected_Gender.getText().toString();
                            registerNewAccount(txtUserName, txtEmail, txtPassword, txtMobile, selectGender);
                        }
                    }
                }
            }
        });

    }
    private Void registerNewAccount(final String username,final String email,final String password,final String mobile,final String gender) {
        final ProgressDialog.progressDialog = new ProgressDialog(RegisterActivity.this);
        ProgressDialog.setCancelable(false);
        ProgressDialog.setIndeterminate(false);
        ProgressDialog.setTitle("Registering New Account");
        ProgressDialog.show();
        String uRl = "http:10.0.2.2/loginregister/register.php";
        StringRequest request = new StringRequest(Request.Method.POST, uRl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Successfully Registered")) {
                    ProgressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    finish();
                } else {
                    ProgressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                public void onErrorResponse (VolleyError error)
                ProgressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("username", username);

                param.put("email", email);
                param.put("psw", password);
                param.put("mobile", mobile);
                param.put("gender", gender);
                return param;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getmInstance(RegisterActivity.this).addToRequestQueue(request);

        @Override
        public void onResponse (String response){


        }

        //  },
        new Response.ErrorListener() {
            @Override

        }; {
            @Override
            protected Map<String, String> getParams () throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("username", username);
                param.put("email", email);
                param.put("psw", password);
                param.put("mobile", mobile);
                param.put("gender", gender);
                return param;
            }
        } ;
    }
}
