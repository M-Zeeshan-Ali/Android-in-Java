package com.codewithdr.bus_ticket_system;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TimePicker;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class bus_details extends AppCompatActivity {

//variables name is declare in bus_detail button option
    Toolbar toolbar;
    AppCompatEditText bus;
    AppCompatSpinner cityname;
    AppCompatButton addbus;
    RecyclerView recyclerView;
    List<bus_class> bus_classes;    //in List<bus_class> specific data onject declared.//list of bus class object.
    List<String> adapter;       //adapter variable is used to help multiple record view.
    AppCompatTextView stime,etime; //AppcomCompatText is variable to used compatiblity with our xml file start time and end time
//data binding
    @Override   //method declared           //protected keyword is used to protect this method within the class
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_details);

        toolbar=findViewById(R.id.toolbar);
        stime=findViewById(R.id.stime);
        etime=findViewById(R.id.etime);

        bus_classes=new ArrayList<>();
        toolbar.setTitle("Bus Detail");         //on toolbar we set the time bus detail

        stime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePicker timePicker;  // digital clock show using TimePicker class
                Calendar calendar=Calendar.getInstance();
                int hour=calendar.get(Calendar.HOUR_OF_DAY);        //get the hour of bus time either am or pm
                int mint=calendar.get(Calendar.MINUTE);             //get the minute from calander and assing id variable name .MINUTE

                //TimePickerDilaog object is created,nested class of alertDialog
                TimePickerDialog timePickerDialog=new TimePickerDialog(bus_details.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override   //onTimeSet function(parameter pass)
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                String amPm;        //string class define am and pm variable ,if condition call
                                if (hourOfDay >= 12) {
                                    amPm = "PM";
                                } else {
                                    amPm = "AM";
                                }
                                                                    //concatinate the hourofday ,minute and ampm variable
                                stime.setText(String.valueOf(hourOfDay+" "+minute+" "+amPm));

                            }
                        },hour,mint,false);     //if 24 hourview then condition is not true
            timePickerDialog.show();    //show the timepickerDialog
            }
        });
                //end of time is selected to call click listener class
        etime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int hour=calendar.get(Calendar.HOUR_OF_DAY);
                int mint=calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog=new TimePickerDialog(bus_details.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                String amPm;
                                if (hourOfDay >= 12) {
                                    amPm = "PM";
                                } else {
                                    amPm = "AM";
                                }

                                etime.setText(String.valueOf(hourOfDay+" "+minute+" "+amPm));

                            }
                        },hour,mint,false);
                timePickerDialog.show();

            }
        });

        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)     /*when click the navigation bar then intent class show to move next activity,
            busdetail is initial activity or 1st activity ,this is used calling 1st activity and move to admin_dashboard is second acvitiy .class is calling the java class*/
            {
                Intent intent=new Intent(bus_details.this,admin_dashboard.class);
                startActivity(intent);
                //onBackPressed();
            }
        });

        bus=findViewById(R.id.bus);
        cityname=findViewById(R.id.cityname);
        addbus=findViewById(R.id.addbus);
        recyclerView=findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter=new ArrayList<>();
        adapter.add("Select City");
        getCity();


        addbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_bus();
            }
        });
        view_bus();
    }
//private is used to not show publicilty ,private class is used only specific user and admin view the information.
    private void add_bus() {
        String name;
        String cname=cityname.getSelectedItem().toString();
        name=bus.getText().toString();          //name variable is assign ,gettext convert into string value
        String tim1=stime.getText().toString();
        String tim2=etime.getText().toString();
        AlertDialog.Builder alert=new AlertDialog.Builder(bus_details.this);
        if(TextUtils.isEmpty(name))
        {
            alert.setMessage("Please enter the bus").setNegativeButton("Retry",null).create().show();
        }
        else if(cname.equals("Select City"))
        {
            alert.setMessage("PLease select the city").setNegativeButton("Retry",null).create().show();
        }
        else if(tim1.equals("Get Start Time") || tim2.equals("Get End Time"))
        {
            alert.setMessage("PLease select the time").setNegativeButton("Retry",null).create().show();

        }

        else
        {
            Log.d("",""+linkapi.url + "addbus.php?name=" + name + "&city="+cname+"&stime="+tim1+"&etime="+tim2+"");
            JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, linkapi.url + "addbus.php?name=" + name + "&city="+cname+"&stime="+tim1+"&etime="+tim2+"",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String res = response.getString("success");
                                if(res.equals("true"))
                                {
                                    Intent intent=new Intent(bus_details.this,bus_details.class);
                                    startActivity(intent);

                                }
                                else if(res.equals("already"))
                                {
                                    AlertDialog.Builder alert=new AlertDialog.Builder(bus_details.this);
                                    alert.setMessage("Already Added").setNegativeButton("Retry",null).create().show();
                                }
                                else{
                                    AlertDialog.Builder alert=new AlertDialog.Builder(bus_details.this);
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
                            ArrayAdapter<String> ad=new ArrayAdapter<>(bus_details.this,R.layout.support_simple_spinner_dropdown_item,adapter);
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
    private void view_bus() {

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
                                bus_classes.add(new bus_class(
                                        jsonObject.getInt("bid"),
                                        jsonObject.getString("bname"),
                                        jsonObject.getString("cname"),
                                        jsonObject.getString("stime"),
                                        jsonObject.getString("etime")
                                ));

                                Log.d(""+jsonObject.getInt("bid"),""+jsonObject.getString("cname"));
                            }
                            bus_adapter adapter=new bus_adapter(bus_details.this, bus_classes);
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