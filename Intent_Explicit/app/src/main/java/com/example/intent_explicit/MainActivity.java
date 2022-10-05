package com.example.intent_explicit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void btn1(View view) {
       /*
        Intent intent=new Intent(getApplicationContext(),Exclipit_Activity.class);
        startActivity(intent);

        */

        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.facebook.com/mrshani.mirza"));
        startActivity(intent);
    }
}