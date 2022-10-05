package com.example.zeeshan_ratingbar_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn;
    RatingBar rating;
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rating=findViewById(R.id.rating);
        btn=findViewById(R.id.btn);

        textview=findViewById(R.id.txt);    //textview variable is used to show the result permanently of textView bar.
        //on button SetOnClick Listener method is used.
        btn.setOnClickListener(new View.OnClickListener() {
            @Override //onClick function used
            public void onClick(View v) {
                //String is a keyword ,variable name is rate also initialize(String.valueof is a method to get the rating value
                String rate =String.valueOf(rating.getRating());

              //  Toast.makeText(getApplicationContext(),rate,Toast.LENGTH_LONG).show();

                textview.setText(rate);  // setText method is used on textview (on rate variable string type)
            }
        });


    }
}