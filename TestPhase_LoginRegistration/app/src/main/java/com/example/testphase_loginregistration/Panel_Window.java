package com.example.testphase_loginregistration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Panel_Window extends AppCompatActivity {
    AppCompatButton admin,user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_window);

        admin=findViewById(R.id.admin_btn);
        user=findViewById(R.id.user_btn);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Panel_Window.this,Admin_Login.class);
                startActivity(i);
            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Panel_Window.this,User_Login.class);
                startActivity(i);
            }
        });
    }
}