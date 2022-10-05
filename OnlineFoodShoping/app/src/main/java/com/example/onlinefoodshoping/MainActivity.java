package com.example.onlinefoodshoping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CheckBox shawarma,burger,kharrai,tea;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//shawarma=to get checkBox  findViewById is a function to get resource folder and id.
        shawarma=(CheckBox) findViewById(R.id.checkBox1);
        burger=(CheckBox) findViewById(R.id.checkBox2);
        kharrai=(CheckBox) findViewById(R.id.checkBox3);
        tea=(CheckBox) findViewById(R.id.checkBox4);
    //submit button is initialize from Button keyword and get the id by xml
        submit= (Button) findViewById(R.id.button);
    //clicklistener method is calling on submit variable
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            //click event method is started from view class
            public void onClick(View view) {

                int amount=0;   //amount varibe is initialize of int type .
                    //StringBuilder Object is created
                StringBuilder result= new StringBuilder();
                    result.append("you have purchased");    //append keyword is used to concatenate with result.

                    if(shawarma.isChecked())    //if condition is checked or checbox of shawarma is checked then message shown {on braces}
                    {
                        result.append("\n Shawarma $1: ");
                        amount=amount+1;
                    }
                if(burger.isChecked())
                {
                    result.append("\n burger $2: ");
                    amount=amount+2;
                }
                if(kharrai.isChecked())
                {
                    result.append("\n kharrai $10: ");
                    amount=amount+10;
                }
                if(tea.isChecked())
                {
                    result.append("\n tea $2: ");
                    amount=amount+2;
                }

                result.append(("\n Total Amount:"+amount+"$"));
                //toast is method to show the popup message with the help of makText method(methodcall,messageconvert in string,toast length shown)
                Toast.makeText(getApplicationContext(),result.toString() , Toast.LENGTH_SHORT)
                      .show();    //show the toast message
            }
        });


    }
}