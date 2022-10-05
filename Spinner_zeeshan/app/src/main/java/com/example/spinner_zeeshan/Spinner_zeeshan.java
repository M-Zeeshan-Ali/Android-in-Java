package com.example.spinner_zeeshan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
//when different class is inherit we use implements keyword ,on AdapterView class we call only OnItemSelectedListener method
public class Spinner_zeeshan extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
String[] users={
        "ZeeshanAli","CS619","VU","MC190404297",};  //string type array is initialized
Spinner spinner;    //spinner is a class which is used to View ListView in a scrolling item,for select the multiple item of only one
TextView text;      //TexView is a class declare a variable text.

    @Override       //when our activity is start on android studio so first onCreate method is call or create
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //super keyword is used for calling the parent class of constructor
        setContentView(R.layout.activity_spinner_zeeshan);  //set the activity on UI (activity_spinner_zeeshan)
        //spinner class initialize with the help of findViewById function on xml id.
        spinner=(Spinner) findViewById(R.id.spinner);
        text=(TextView) findViewById(R.id.text);

        //ArrayAdapter object is created
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(
                                            //calling the simple_spinner_item,on user string variable
                this, android.R.layout.simple_spinner_item,users);
        //adapterClass is used and set the DropDownViewResource method.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Dot operator is used to access the property of adapter .
        spinner.setAdapter(adapter);    //set the setAdapter(variable name)method on spinner vairable.
        spinner.setOnItemSelectedListener(this);//set the setOnItemSelectedListener method on spinner variable
                                        //this is a keyword for using the implement of current object activity
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               //int pos
                               int position, long id) {              //users[pos]
      //  Toast.makeText(getApplicationContext(), "Selected User:"+users[position], Toast.LENGTH_SHORT).show();

       text.setText(users[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}