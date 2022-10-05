package com.codewithdr.bus_ticket_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class userdetails extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    List<user_class> user_classes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetails);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("User Detail");

        recyclerView = findViewById(R.id.recycleview);
        user_classes = new ArrayList<>();

        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(userdetails.this, admin_dashboard.class);
                startActivity(intent);
                //onBackPressed();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getUser();
    }
        void
        getUser(){
            Log.d("",""+ linkapi.url+"view_user.php");
            StringRequest stringRequest=new StringRequest(Request.Method.GET, linkapi.url + "view_user.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try
                            {
                                JSONArray jsonArray=new JSONArray(response);
                                for(int i=0;i<jsonArray.length();i++)
                                {
                                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                                    user_classes.add(new user_class(
                                            jsonObject.getInt("uid"),
                                            jsonObject.getString("uname"),
                                            jsonObject.getString("uemail"),
                                            jsonObject.getString("uphoneno"),
                                            jsonObject.getString("ucnic"),
                                            jsonObject.getString("upassword"),
                                            jsonObject.getString("status")
                                            ));

                               }

                                user_adapter user_adapter=new user_adapter(getApplicationContext(),user_classes);
                                recyclerView.setAdapter(user_adapter);

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