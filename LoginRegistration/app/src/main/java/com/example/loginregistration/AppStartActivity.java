package com.example.loginregistration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

//import androidx.appcompat.widget.Toolbar;
//import android.support.v7.widget.Toolbar
//import android.widget.Toolbar;
//import androidx.appcompat.widget.Toolbar
//import android.support.v7.widget.Toolbar

public class AppStartActivity extends AppCompatActivity {

    Button logout;
    // Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_start);
      //  logout=findViewById(R.id.logout);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("App Start Activity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final SharedPreferences sharedPreferences=getSharedPreferences("UserInfo",MODE_PRIVATE);

        logout=findViewById(R.id.logout);
       logout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               SharedPreferences.Editor editor=sharedPreferences.edit();
               editor.putString(getResources().getString(R.string.prefLoginState),"loggedout");
               editor.apply();

               startActivity(new Intent(AppStartActivity.this,MainActivity.class));
               finish();
           }
       });


    }
}