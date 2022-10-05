package com.example.alertdialogpracticeexample; //package name of my app.
//android app library include.
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/*AppCompatActivity is a parent class inherited by extends keyword to mainactivity
and class name is MainActivity*/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*super.is used for to call the parent class of constructor(while super is keyword) when
        our activtiy is start first onCreate method is call */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // setContentView is a method for set the content in Main Activity of our app

    }
        //open dialog method is created ,void is keyword for not return the value (view v ,is parameter passing .
    public void openDialog(View v){
//Alert dialog is a class and builder is the nested class of AlertDialog
        //AlertDialog is a parent class and .builder is a child class of Alertdialog
        //object is created of AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //Setting AlertDialog Characteristics
        // Builder.SetTile is used for set the title
        builder.setTitle("Learning Dialog");
        //for setMessage Calling
        builder.setMessage("I Love Study of IT");

        //Set Positive Button
        //for set the positiveButton object is created ,Event Created on OK Button by using OnClickListener
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            //onclick method is create  within AlertDialog parent class using the nested builder class.
            public void onClick(DialogInterface dialog, int which) {
                // Toast is used for popup message by using the maketext method with parmeter passing ,text,and toast lenghth method
                Toast.makeText(getApplicationContext(),"OK button was pressed",Toast.LENGTH_LONG)
                        .show();        //.show method is used to show the text

            }
        }); //end of the nested builder class of AlertDialog parent Class.

        //Set Negative Button

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(getApplicationContext(),"Cancel button was pressed",Toast.LENGTH_LONG)
                        .show();    //Display the Toast message

            }
        });

        //Set Neutral Button
        builder.setNeutralButton("Remind me later", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(getApplicationContext(),"Remind me later button was pressed",Toast.LENGTH_LONG).show();

            }
        });

        //Creating AlertDialog
        AlertDialog dialog = builder.create();

        //Displaying AlertDialog
        dialog.show();
    }
}