package com.example.testphase_loginregistration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class City_Details extends AppCompatActivity {
    Toolbar toolbar;
    AppCompatEditText city;
    AppCompatButton addcity;
    RecyclerView recyclerView;
    List<city_class> cityList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_details);

        cityList=new ArrayList<>();

        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("City Detail");
        //toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(City_Details.this,Admin_Dashboard.class);
                startActivity(intent);  //onBackPressed();
            }
        });

        city=findViewById(R.id.city);
        addcity=findViewById(R.id.addcity);
        recyclerView=findViewById(R.id.recycleview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addcity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addcity();
            }
/*
            private void addcity() {
                String name;
                name=city.getText().toString();
                AlertDialog.Builder alert=new AlertDialog.Builder(City_Details.this);
                if(TextUtils.isEmpty(name)){
                    alert.setMessage("please enter the city")
                            .setNegativeButton("Retry",null)
                            .create().show();
                }
                else{ Log.d("",""+linkapi.url+"addcity.php?name"+name+"");
                    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, linkapi.url + "addcity.php?name" + name + ""
                            , null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
                    Volley.newRequestQueue(getApplicationContext()
                                                                    .add(jsonObjectRequest));
                }

            }
*/

        });
        view_city();
    }
    private void   view_city(){
        Log.d("",""+linkapi.url+"view_city.php");
        StringRequest stringRequest= new StringRequest(Request.Method.GET, linkapi.url + "view_city.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONArray jsonArray=new JSONArray(response);
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                cityList.add(new city_class(
                                        jsonObject.getInt("cid"),
                                        jsonObject.getString("cname")
                                ));
                                Log.d(""+jsonObject.getInt("cid"),
                                        ""+jsonObject.getString("cname"));
                            }
                            city_adapter adapter=new city_adapter(City_Details.this,cityList);
                            recyclerView.setAdapter(adapter);
                        }
                        catch(Exception ex){
                            Log.d("",""+ex);
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
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

    }
    private void addcity() {
        String name;
        name=city.getText().toString();
        AlertDialog.Builder alert=new AlertDialog.Builder(City_Details.this);
        if(TextUtils.isEmpty(name)){
            alert.setMessage("please enter the city")
                    .setNegativeButton("Retry",null)
                    .create().show();
        }
        else{ Log.d("",""+linkapi.url+"addcity.php?name"+name+"");
            JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, linkapi.url + "addcity.php?name" + name + ""
                    , null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try{
                                String res=response.getString("success");
                                if(res.equals("true")){
                                    Intent intent =new Intent(City_Details.this,City_Details.class);
                                    startActivity(intent);
                                } else if(res.equals("already")){
                                    AlertDialog.Builder alert=new AlertDialog.Builder(City_Details.this);
                                    alert.setMessage("Already added").setNegativeButton("Retry",null).create().show();
                                }
                                else{
                                    AlertDialog.Builder alert=new AlertDialog.Builder(City_Details.this);
                                    alert.setMessage("some thing went wrong").setNegativeButton("Retry",null).create().show();
                                }
                            }
                            catch(Exception ex){
                                Log.d("Exception",""+ex);
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            Volley.newRequestQueue(getApplicationContext()).add(jsonObjectRequest);
        }

    }

    }
