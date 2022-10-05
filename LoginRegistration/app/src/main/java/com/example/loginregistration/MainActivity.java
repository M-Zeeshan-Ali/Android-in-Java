package com.example.loginregistration;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    //MaterialEditText
    MaterialEditText email,password;
    Button login,register;
    CheckBox loginState;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences=getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        setContentView(R.layout.activity_main);


        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        loginState=findViewById(R.id.checkbox);
        login=findViewById(R.id.login);
        register=findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtEmail=email.getText().toString();
                String txtpassword=password.getText().toString();

                if(TextUtils.isEmpty(txtEmail)||(TextUtils.isEmpty(txtpassword))
                {
                    Toast.makeText(MainActivity.this, "All Field Required", Toast.LENGTH_SHORT).show();
                }
                else{
                    login(txtEmail.txtpassword);
                }
            }
        });

        String loginStatus= sharedPreferences.getString(getResources().getString(R.string.prefLoginState),"");
        if(loginStatus.equals("loggedin")){
            startActivity(new Intent(MainActivity.this,AppStartActivity.class));
        }



    }

    private HashMap<String, String> login(String email, String password){
        final ProgressDialog progressDialog=new progressDialog (MainActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Registering New Account");
        progressDialog.show();
        String uRl="http:10.0.2.2/loginregister/register.php";

        StringRequest request= new StringRequest(Request.Method.POST,uRl,new Response.Listener<String>()) {
            @Override
            public void onResponse(String response) {
                if (response.equals("Login Success")) {
                    ProgressDialog.dismiss();
                    Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    //  editor.putString(getResources().getString(R.string.prefLoginState),);
                    if (loginState.isChecked()) {
                        editor.putString(getResources().getString(R.string.prefLoginState), "loggedin");
                    } else {
                        editor.putString(getResources().getString(R.string.prefLoginState), "loggedout");
                    }
                    editor.apply();

                    startActivity(new Intent(MainActivity.this, AppStartActivity.class));
                } else {
                    ProgressDialog.dismiss();
                    Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();}
            }
        },  new Response.ErrorListener(){
            @Override
            public  void  onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(MainActivity.this,error.toString(), Toast.LENGTH_SHORT).show();
            }



       /* },new Response.ErrorListener(){
            @Override
            public  void  onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(MainActivity.this,error.toString(), Toast.LENGTH_SHORT).show();
*/

        };
        {
            @Override
                    //protected Map<String, String> getParams () throws AuthFailureError
            protected Map<String, String> getParams () throws AuthFailureError {
                HashMap<String,String> param=new HashMap<>();

                param.put("email",email);
                param.put("psw",password);

                return param;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(30000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getmInstance(MainActivity.this).addToRequestQueue(request);

    }
}