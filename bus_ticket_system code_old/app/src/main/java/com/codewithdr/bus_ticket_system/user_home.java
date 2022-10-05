package com.codewithdr.bus_ticket_system;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class user_home extends Fragment {

    AppCompatSpinner city,bus,to_address,end_address;
    AppCompatButton btnsearch;
    static String saddress,eaddress;

    RecyclerView recyclerView;
    List<bus_class> bus_classes;


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());

        View view=layoutInflater.inflate(R.layout.user_home,null,true);

        city=view.findViewById(R.id.city);
        bus=view.findViewById(R.id.bus);
        to_address=view.findViewById(R.id.to_address);
        end_address=view.findViewById(R.id.end_address);

        btnsearch=view.findViewById(R.id.search_bus);
        bus_classes=new ArrayList<>();
        recyclerView=view.findViewById(R.id.view_bus);

        getticket();


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest=new StringRequest(Request.Method.GET, linkapi.url + "view_bus_user.php?bnumber="+bus.getSelectedItem().toString()+"",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try
                                {
                                    JSONArray jsonArray=new JSONArray(response);
                                    for(int i=0;i<jsonArray.length();i++)
                                    {
                                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                                        bus_classes.add(new bus_class(
                                                jsonObject.getInt("bid"),
                                                jsonObject.getString("bname"),
                                                jsonObject.getString("cname"),
                                                jsonObject.getString("stime"),
                                                jsonObject.getString("etime")
                                        ));

                                        Log.d(""+jsonObject.getInt("bid"),""+jsonObject.getString("cname"));
                                    }
                                    bus_adapter_user adapter=new bus_adapter_user(getContext(), bus_classes);
                                    recyclerView.setAdapter(adapter);
                                    saddress=to_address.getSelectedItem().toString();
                                    eaddress=end_address.getSelectedItem().toString();
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

                Volley.newRequestQueue(getContext()).add(stringRequest);
            }
        });

        getCity();


        return view;
    }

    private void getticket() {


    }

    void
    getCity(){
        List<String> listcity=new ArrayList<>();
        Log.d("",""+ linkapi.url+"view_city.php");
        listcity.add("Select City");
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

                                listcity.add(jsonObject.getString("cname"));

                                Log.d(""+jsonObject.getInt("cid"),""+jsonObject.getString("cname"));
                            }
                            ArrayAdapter<String> ad=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,listcity);
                            city.setAdapter(ad);
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

        Volley.newRequestQueue(getContext()).add(stringRequest);



        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Home",""+listcity.get(position));
                if(listcity.get(position).equals("Select City"))
                {

                }
                else
                {
                    getBus(listcity.get(position));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    void
    getBus(String c){

        List<String> listbus=new ArrayList<>();
        listbus.add("Select Bus");
        Log.d("",""+ linkapi.url+"view_bus1.php?cname="+c+"");
        StringRequest stringRequest=new StringRequest(Request.Method.GET, linkapi.url+"view_bus1.php?cname="+c+"",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try
                        {
                            JSONArray jsonArray=new JSONArray(response);
                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject=jsonArray.getJSONObject(i);

                                listbus.add(jsonObject.getString("bname"));

                            }
                            ArrayAdapter<String> ad=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,listbus);
                            bus.setAdapter(ad);
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

        Volley.newRequestQueue(getContext()).add(stringRequest);
        bus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Home",""+listbus.get(position));
                if(listbus.get(position).equals("Select Bus"))
                {

                }
                else
                {
                    getroute(listbus.get(position));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    void
    getroute(String b){

        List<String> listroute=new ArrayList<>();
        listroute.add("Select Start Address");
        List<String> listroute1=new ArrayList<>();
        listroute1.add("Select End Address");

        Log.d("",""+ linkapi.url+"view_route1.php?bname="+b+"");
        StringRequest stringRequest=new StringRequest(Request.Method.GET, linkapi.url+"view_route1.php?bname="+b+"",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try
                        {
                            JSONArray jsonArray=new JSONArray(response);
                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject=jsonArray.getJSONObject(i);

                                listroute.add(jsonObject.getString("rname"));
                                listroute1.add(jsonObject.getString("rname"));

                            }
                            ArrayAdapter<String> ad=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,listroute);
                            ArrayAdapter<String> ad1=new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item,listroute1);

                            to_address.setAdapter(ad);
                        end_address.setAdapter(ad1);
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

        Volley.newRequestQueue(getContext()).add(stringRequest);


    }
}
