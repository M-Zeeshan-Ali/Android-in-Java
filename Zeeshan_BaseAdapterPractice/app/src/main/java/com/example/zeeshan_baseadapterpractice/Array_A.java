package com.example.zeeshan_baseadapterpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Array_A extends AppCompatActivity {
ListView listview;
String names[]={
        "Zeeshan",
        "Faizan",
        "khalid",
        "Sarmad"
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array);

        listview=(ListView) findViewById(R.id.listview);

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.activity_array_b,R.id.textview,names);
            listview.setAdapter(arrayAdapter);
    }
}