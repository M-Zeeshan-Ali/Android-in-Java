package com.codewithdr.bus_ticket_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class panel_window extends AppCompatActivity {


    AppCompatButton admin,user;     //main app panel which incluse admin and user button

//binding the data
    @Override       // annotation predefined function.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_window);

        admin=findViewById(R.id.btn_admin);     // admin button ,admin varialble is assign by id from xml file.
        user=findViewById(R.id.btn_user);       //user button ,user variable is assign by xml id .btn_user

        //admin class call ,clicklistener is assign on admin button
        admin.setOnClickListener(new View.OnClickListener() {
            @Override           //apply the condition ,when admin button is clicked and intent variable call to next activity
            public void onClick(View v) {
                Intent intent=new Intent(panel_window.this,admin_login.class);
                startActivity(intent);
            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(panel_window.this,user_login.class);
                startActivity(intent);

            }
        });


    }
}