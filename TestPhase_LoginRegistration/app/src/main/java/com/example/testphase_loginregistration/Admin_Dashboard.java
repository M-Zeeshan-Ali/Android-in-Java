package com.example.testphase_loginregistration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Admin_Dashboard extends AppCompatActivity {
    AppCompatButton btncity,btnroute,btnuser,btnlogout,btnbus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        btncity=(AppCompatButton) findViewById(R.id.btncity);
        btnroute=(AppCompatButton) findViewById(R.id.btnroute);
        btnuser=(AppCompatButton) findViewById(R.id.btnuser);
        btnbus=(AppCompatButton) findViewById(R.id.btnbus);
        btnlogout=(AppCompatButton) findViewById(R.id.btnlogout);

        btnbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Admin_Dashboard.this,Bus_Details.class);
                startActivity(i);
            }
        });

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Admin_Dashboard.this,Panel_Window.class);
                startActivity(i);
            }
        });
        btnroute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Admin_Dashboard.this,Route_Details.class);
                startActivity(i);
            }
        });

        btnuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Admin_Dashboard.this,User_Details.class);
                startActivity(i);
            }
        });
        btncity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Admin_Dashboard.this,City_Details.class);
                startActivity(i);
            }
        });


    }
}