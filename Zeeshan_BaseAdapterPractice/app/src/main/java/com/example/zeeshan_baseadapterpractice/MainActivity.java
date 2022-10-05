package com.example.zeeshan_baseadapterpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {
    GridView simplegridview;

    int images[]={R.drawable.a,R.drawable.b,R.drawable.c,
            R.drawable.a,R.drawable.b,R.drawable.c,
            R.drawable.a,R.drawable.b,R.drawable.c,};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simplegridview=(GridView)findViewById(R.id.simplegridview);
        CustomAdapter customAdapter=new CustomAdapter(getApplicationContext(),images);
        simplegridview.setAdapter(customAdapter);



    }
}