package com.codewithdr.bus_ticket_system;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

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

public class routedetail extends AppCompatActivity {
    Toolbar toolbar;
    AppCompatEditText route;
    AppCompatSpinner cityname,busname;
    AppCompatButton addroute;
    RecyclerView recyclerView;
    List<route_class> route_classes;
    List<String> adapter;
    List<String> adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routedetail);

        toolbar=findViewById(R.id.toolbar);
        route_classes=new ArrayList<>();
        toolbar.setTitle("Route Detail");
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        //on toolbar for backarrow ,setNavigationOnClickListener method is applied.
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(routedetail.this,admin_dashboard.class);
                startActivity(intent);
                //onBackPressed();
            }
        });

        route=findViewById(R.id.route);
        busname=findViewById(R.id.busname);
        cityname=findViewById(R.id.cityname);
        addroute=findViewById(R.id.addroute);
        recyclerView=findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter=new ArrayList<>();
        adapter.add("Select City");
        adapter1=new ArrayList<>();
        adapter1.add("Select Bus");
        getCity();
        getBus();
        view_route();
        addroute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_route();
            }
        });
    }

    private void add_route() {
        String name;
        String cname=cityname.getSelectedItem().toString();
        String bname=busname.getSelectedItem().toString();

        name=route.getText().toString();
        AlertDialog.Builder alert=new AlertDialog.Builder(routedetail.this);
        if(TextUtils.isEmpty(name))
        {
            alert.setMessage("Please enter the bus").setNegativeButton("Retry",null).create().show();
        }
        else if(cname.equals("Select City"))
        {
            alert.setMessage("PLease select the city").setNegativeButton("Retry",null).create().show();
        }
        else if(bname.equals("Select Bus"))
        {
            alert.setMessage("PLease select the bus").setNegativeButton("Retry",null).create().show();
        }


        else
        {
            Log.d("",""+linkapi.url + "addroute.php?name=" + name + "&city="+cname+"&bus="+bname+"");
            JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, ""+linkapi.url + "addroute.php?name=" + name + "&city="+cname+"&bus="+bname+"",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String res = response.getString("success");
                                if(res.equals("true"))
                                {
                                    Intent intent=new Intent(routedetail.this,routedetail.class);
                                    startActivity(intent);

                                }
                                else if(res.equals("already"))
                                {
                                    AlertDialog.Builder alert=new AlertDialog.Builder(routedetail.this);
                                    alert.setMessage("Already Added").setNegativeButton("Retry",null).create().show();


                                }
                                else{
                                    AlertDialog.Builder alert=new AlertDialog.Builder(routedetail.this);
                                    alert.setMessage("Some thing went wrong").setNegativeButton("Retry",null).create().show();
                                }
                            }
                            catch (Exception ex)
                            {
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

    void
    getCity(){
        Log.d("",""+ linkapi.url+"view_city.php");
        StringRequest stringRequest=new StringRequest(Request.Method.GET, linkapi.url + "view_city.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try
                        {
                            JSONArray jsonArray=new JSONArray(response);
                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject=jsonArray.getJSONObject(i);

                                adapter.add(jsonObject.getString("cname"));

                                Log.d(""+jsonObject.getInt("cid"),""+jsonObject.getString("cname"));
                            }
                            ArrayAdapter<String> ad=new ArrayAdapter<>(routedetail.this,R.layout.support_simple_spinner_dropdown_item,adapter);
                            cityname.setAdapter(ad);
                        }
                        catch (Exception ex)
                        {
                            Log.d("",""+ex);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);


    }

    void
    getBus(){
        Log.d("",""+ linkapi.url+"view_bus.php");
        StringRequest stringRequest=new StringRequest(Request.Method.GET, linkapi.url + "view_bus.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try
                        {
                            JSONArray jsonArray=new JSONArray(response);
                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject=jsonArray.getJSONObject(i);

                                adapter1.add(jsonObject.getString("bname"));

                            }
                            ArrayAdapter<String> ad=new ArrayAdapter<>(routedetail.this,
                                    R.layout.support_simple_spinner_dropdown_item,adapter1);
                            busname.setAdapter(ad);
                        }
                        catch (Exception ex)
                        {
                            Log.d("",""+ex);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);


    }
    private void view_route() {

        Log.d("",""+ linkapi.url+"view_route.php");
        StringRequest stringRequest=new StringRequest(Request.Method.GET, linkapi.url + "view_route.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try
                        {
                            JSONArray jsonArray=new JSONArray(response);
                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                route_classes.add(new route_class(
                                        jsonObject.getInt("rid"),
                                        jsonObject.getString("rname"),
                                        jsonObject.getString("cname"),
                                        jsonObject.getString("bname")
                                ));

                                Log.d(""+jsonObject.getInt("rid"),""+jsonObject.getString("cname"));
                            }
                            route_adapter adapter=new route_adapter(routedetail.this, route_classes);
                            recyclerView.setAdapter(adapter);

                        }
                        catch (Exception ex)
                        {
                            Log.d("",""+ex);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

    }
}