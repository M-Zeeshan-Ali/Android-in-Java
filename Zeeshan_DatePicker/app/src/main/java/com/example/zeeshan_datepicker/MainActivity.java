package com.example.zeeshan_datepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    DatePicker picker;
    TextView textview;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        picker= (DatePicker) findViewById(R.id.picker);
        textview=(TextView) findViewById(R.id.textview);
        button=(Button) findViewById(R.id.button);

        textview.setText("Current Date:" + dateConverter());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textview.setText("Change Date:"+dateConverter());

            }
        });


    }
    public String dateConverter()
    {
        StringBuilder obj = new StringBuilder();
        obj.append((picker.getMonth() + 1)+"/");
        obj.append(picker.getDayOfMonth()+"/");
        obj.append(picker.getYear());
        return obj.toString();
    }
}