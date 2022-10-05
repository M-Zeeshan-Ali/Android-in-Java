package com.example.zeeshan_togglebutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    Button submit;      //in textView Button Class is declared with submit variable.
    ToggleButton tb1, tb2;  //in ToggleButton Class two toogleButton is declared


    Switch sw1, sw2;
    Button submit1;

    @Override   //oncreate method is call form android activity lifcycle method diagram.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);     //set the content of activity_main UI design.
                    //button variable is initialize
        tb1 = (ToggleButton)findViewById(R.id.toggleButton1);
        tb2 = (ToggleButton)findViewById(R.id.toggleButton2);
        submit = (Button)findViewById(R.id.button); //submit Button is initialized.



        sw1 = (Switch) findViewById(R.id.switch1);
        sw2 = (Switch)findViewById(R.id.switch2);
        submit1 = (Button)findViewById(R.id.button1);

        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1,str2;
                if(sw1.isChecked())
                    str1=sw1.getTextOn().toString();
                else
                    str1=sw1.getTextOff().toString();
                if(sw2.isChecked())
                    str2=sw2.getTextOn().toString();
                    else
                        str2=sw2.getTextOff().toString();

     Toast.makeText(getApplicationContext(), "Radio Device -  " + str1 + " \n" + "Wifi - "
             + str2,Toast.LENGTH_SHORT).show();

            }
        });

    }
    //method created of view Activity with the method1
    public void Method1(View view) {
        StringBuilder result = new StringBuilder();
        result.append("\nYour Radio Device: ").append(tb1.getText());

        Toast.makeText(getApplicationContext(),result.toString(),Toast.LENGTH_LONG).show();
    }

    public void Method2(View v) {
        StringBuilder result = new StringBuilder();
        result.append("\nYour Wifi Device: ").append(tb2.getText());

        Toast.makeText(getApplicationContext(),result.toString(),Toast.LENGTH_LONG).show();
    }

    public void Method3(View v) {
        //StringBuilder is a Class name ,result is variable to make an object of StringBuilder.
        StringBuilder result = new StringBuilder();
        //result is a variable .append is a class for calling the reference of append method("statement")
        result.append("Your Radio Device: ")

        //get the message from toogleButton when action performed .getText is a method  . operator is also called member operator for reference calling or sometime calling the constructor.
                .append( tb1.getText());

        result.append("\nYour Wifi Device: ")
                .append(tb2.getText());
//Toast Class call for showing the pop_up message .
        Toast.makeText(getApplicationContext(),
                result.toString(),  //result convert into String (usingString method.)
                Toast.LENGTH_LONG)  //Toast message time duration
                .show();    //show method call
    }
}