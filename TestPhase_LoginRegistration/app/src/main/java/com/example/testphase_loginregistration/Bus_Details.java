package com.example.testphase_loginregistration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TimePicker;
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
import java.util.Calendar;
import java.util.List;

public class Bus_Details extends AppCompatActivity {
Toolbar toolbar;
AppCompatEditText bus;
AppCompatButton addbus;
AppCompatSpinner cityname;

RecyclerView recyclerView;
List<bus_class> bus_classes;
List<String> adapter;
AppCompatTextView stime,etime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_details);

        toolbar = findViewById(R.id.toolbar);
        stime = findViewById(R.id.stime);
        etime = findViewById(R.id.etime);

        bus_classes = new ArrayList<>();
        toolbar.setTitle("Bus Detail");

        stime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePicker timePicker;
                Calendar calender = Calendar.getInstance();
                int hour = calender.get(Calendar.HOUR_OF_DAY);
                int mint = calender.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(Bus_Details.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                String amPm;
                                if (hourOfDay >= 12) {
                                    amPm = "PM";
                                } else {
                                    amPm = "AM";
                                }
                                stime.setText(String.valueOf(hourOfDay + "" + minute + "" + amPm));

                            }
                        }
                        , hour, mint, false);
                timePickerDialog.show();
            }
        });
        etime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int mint = calendar.get(calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(Bus_Details.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                String amPm;
                                if (hourOfDay >= 12) {
                                    amPm = "PM";
                                } else {
                                    amPm = "AM";
                                }
                                etime.setText(String.valueOf(hourOfDay + "" + minute + "" + amPm));
                            }
                        }, hour, mint, false
                );
                timePickerDialog.show();
            }
        });
        //    toolbar.setNavigationIcon(R.drawable.ic.baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Bus_Details.this, Admin_Dashboard.class);
                startActivity(i);
                //onBackPressed();
            }
        });

        bus = findViewById(R.id.bus);
        cityname = findViewById(R.id.cityname);
        addbus = findViewById(R.id.addbus);
        recyclerView = findViewById(R.id.recycleview);

        adapter = new ArrayList<>();
        adapter.add("Select Ctiy");
        getCity();
    }
/*
        addbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_bus();
            }
        });
        view_bus();
    }
*/
            private void add_bus(){
                //String name;
                String cname = cityname.getSelectedItem().toString();
                String name = bus.getText().toString();
                String tim1 = stime.getText().toString();
                String tim2 = stime.getText().toString();

                AlertDialog.Builder alert = new AlertDialog.Builder(Bus_Details.this);
                if (TextUtils.isEmpty(name)) {
                    alert.setMessage("please enter the bus").setNegativeButton("Retry", null).create().show();
                } else if (cname.equals("Select City")) {
                    alert.setMessage("please enter the City").setNegativeButton("Retry", null).create().show();
                } else if (tim1.equals("Get Start Time") || tim2.equals("Get End Time")) {
                    alert.setMessage("please enter the City").setNegativeButton("Retry", null).create().show();
                } else {
                    Log.d("", "" + linkapi.url + "addbus.php?name=" + name + "&city=" + cname + "&stime=" + tim1 + "&etime=" + tim2 + "");
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                            linkapi.url + "addbus.php?name=" + name + "&city=" + cname + "&stime=" + tim1 + "&etime=" + tim2 + "",
                            null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {


                                    try {
                                        String res = response.getString("success");
                                        if (res.equals("true")) {
                                            Intent i = new Intent(Bus_Details.this, Bus_Details.class);
                                            startActivity(i);
                                        } else if (res.equals("already")) {
                                            AlertDialog.Builder alert = new AlertDialog.Builder(Bus_Details.this);
                                            alert.setMessage("Already Added").setNegativeButton("Retry", null)
                                                    .create()
                                                    .show();
                                        } else {
                                            AlertDialog.Builder alert = new AlertDialog.Builder(Bus_Details.this);
                                            alert.setMessage("some thing went wrong")
                                                    .setNegativeButton("Retry", null).create().show();
                                        }

                                    } catch (Exception ex) {
                                        Log.d("Exception", "" + ex);
                                    }

                                }

                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    }
                    );
                    Volley.newRequestQueue(getApplicationContext()).add(jsonObjectRequest);

                }


            }

            void
            getCity() {
                Log.d("", "" + linkapi.url + "view_city.php");
                StringRequest stringRequest = new StringRequest(Request.Method.GET, linkapi.url + "view_city.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONArray jsonArray = new JSONArray(response);
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                                        adapter.add(jsonObject.getString("cname"));
                                        Log.d("" + jsonObject.getInt("cid"), "" + jsonObject.getString("cname"));
                                    }                                                                  //R.layout.support_simple_spinner_dropdown_item,adapter
                                    ArrayAdapter<String> ad = new ArrayAdapter<>(Bus_Details.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, adapter);
                                    cityname.setAdapter(ad);
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
                Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
            }



        private void View_bus() {
            Log.d("", "" + linkapi.url + "view_bus.php");
            StringRequest stringRequest = new StringRequest(Request.Method.GET, linkapi.url + "view_bus.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    bus_classes.add(new bus_class(
                                            jsonObject.getInt("bid"),
                                            jsonObject.getString("bname"),
                                            jsonObject.getString("cname"),
                                            jsonObject.getString("stime"),
                                            jsonObject.getString("etime")
                                    ));

                                    Log.d("" + jsonObject.getInt("bid"), "" + jsonObject.getString("cname"));
                                }
                                bus_adapter adapter = new bus_adapter(Bus_Details.this, bus_classes);
                                recyclerView.setAdapter(adapter);
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
            Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

        }
    }
/*
     void
    getCity() {
         Log.d("",""+ linkapi.url+"view_city.php");
         StringRequest stringRequest=new StringRequest(Request.Method.GET, linkapi.url + "view_city.php",
                 new Response.Listener<String>() {
                     @Override
                     public void onResponse(String response) {
                         try {
                             JSONArray jsonArray = new JSONArray(response);
                             for (int i = 0; i < jsonArray.length(); i++) {
                                 JSONObject jsonObject = jsonArray.getJSONObject(i);

                                 adapter.add(jsonObject.getString("cname"));
                                 Log.d("" + jsonObject.getInt("cid"), "" + jsonObject.getString("cname"));
                             }                                                                  //R.layout.support_simple_spinner_dropdown_item,adapter
                             ArrayAdapter<String> ad = new ArrayAdapter<>(Bus_Details.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, adapter);
                             cityname.setAdapter(ad);
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
         Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

    }
    */
//}

/*
    private void View_bus() {
    }

    private void getCity() {
    }
}

 */