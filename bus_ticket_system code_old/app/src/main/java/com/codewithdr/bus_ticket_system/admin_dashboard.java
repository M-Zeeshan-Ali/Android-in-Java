package com.codewithdr.bus_ticket_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.View;

public class admin_dashboard extends AppCompatActivity {

    AppCompatButton btncity,btnroute,btnuser,btnlogout,btnbus; //variable names of dashboard buttion

//binding the data
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        /*btncity variable is assign on android with R.id.btncity initialize id from xml designing side
        btnroute variable is assing btnroute id from xml designing or backend of the app
        btnuser variable is assign btnuser ,btnlogout is assign xml btnlogout id
        btnbus variable is assign btnbus id by xml file*/
        btncity=findViewById(R.id.btncity);
        btnroute=findViewById(R.id.btnroute);
        btnuser=findViewById(R.id.btnuser);
        btnlogout=findViewById(R.id.btnlogout);
        btnbus=findViewById(R.id.btnbus);
//calling the set of click button by using setOnClickListener like press the button
btnbus.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        /*
        intent class, intent explicit is used to move one activity to another activity
        admin_dashboard is the current activity of admin dashboard ,this is used for calling 1st activity
         bus_details is used where we want to move ,class is used to calling the java file where want to go
         */
        Intent intent=new Intent(admin_dashboard.this,bus_details.class);
        startActivity(intent);
    }
});

//calling the set of click button by using setOnClickListener like press the button btnlogout
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
        intent class, intent explicit is used to move one activity to another activity
        admin_dashboard is the current activity of admin dashboard ,this is used for calling 1st activity
         panel_window is used where we want to move ,class is used to calling the java file where want to go
*/
                Intent intent=new Intent(admin_dashboard.this,panel_window.class);
                startActivity(intent);      //start move one into another activity
            }
        });
//calling the set of click button by using setOnClickListener like press the button btnroute
        btnroute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
intent class, intent explicit is used to move one activity to another activity
admin_dashboard is the current activity of admin dashboard ,this is used for calling 1st activity
route_detail or (last action performed) is used where we want to move ,class is used to calling the java file where want to go
*/
                Intent intent=new Intent(admin_dashboard.this,routedetail.class);
                startActivity(intent);
            }
        });
        btnuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
intent class, intent explicit is used to move one activity to another activity
admin_dashboard is the current activity of admin dashboard ,this is used for calling 1st activity
userdetails or (last action performed) is used where we want to move ,class is used to calling the java file where want to go
*/
                Intent intent=new Intent(admin_dashboard.this,userdetails.class);
                startActivity(intent);

            }
        });
        btncity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
intent class, intent explicit is used to move one activity to another activity
admin_dashboard is the current activity of admin dashboard ,this is used for calling 1st activity
citydetail or (last action performed) is used where we want to move ,class is used to calling the java file where want to go
*/
                Intent intent=new Intent(admin_dashboard.this,citydetail.class);
                startActivity(intent);
            }
        });
    }
}