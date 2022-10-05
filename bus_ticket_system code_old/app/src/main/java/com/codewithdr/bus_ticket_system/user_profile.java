package com.codewithdr.bus_ticket_system;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

public class user_profile extends Fragment {

    AppCompatEditText name,email,password,phoneno,cnic;
    AppCompatButton btn_update,logout;

    String emailPattern = "[a-zA-Z][a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());

        View view=layoutInflater.inflate(R.layout.user_profile,null,true);

        name=view.findViewById(R.id.name);
        email=view.findViewById(R.id.email);
        password=view.findViewById(R.id.password);
        cnic=view.findViewById(R.id.cnic);
        phoneno=view.findViewById(R.id.phone);
        btn_update=view.findViewById(R.id.update);
        logout=view.findViewById(R.id.logout);

        email.setEnabled(false);
        view_user();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),panel_window.class);
                startActivity(intent);
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
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

                    Log.d("",""+linkapi.url+"user_update.php?name="+na+"&email="+em+"&password="+pass+"&phone="+phone+"&cnic="+cni+"");


                }
            }
        });

        return view;
    }

    private void view_user() {

        Log.d("Here","Here");
        StringRequest stringRequest=new StringRequest(
                Request.Method.GET,
                linkapi.url +"user_profile.php?email="+user_login.passemail+"",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.d("Here","Here"+user_login.passemail);

                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Log.d("Here","Here"+jsonObject.getString("uphoneno"));
                                Log.d("Here","Here"+jsonObject.getString("ucnic"));


                                name.setText(jsonObject.getString("uname"));
                                email.setText(jsonObject.getString("uemail"));
                                password.setText(jsonObject.getString("upassword"));
                                phoneno.setText(jsonObject.getString("uphoneno"));
                                cnic.setText(jsonObject.getString("ucnic"));
                            }
                        }
                        catch (Exception ex)
                        {
                            Log.d("Exception",""+ex);

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
        Volley.newRequestQueue(getContext()).add(stringRequest);



    }
}
