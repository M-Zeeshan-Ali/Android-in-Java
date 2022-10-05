package com.example.zeeshan_calculator_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public String str="";
    Character operator ='q';
    double i, num, numtemp;
    EditText showresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        showresult = (EditText)findViewById(R.id.result_id);
    }
    public void insert(int j)
    {
        str = str+ Integer.toString(j);
        num = Double.valueOf(str).doubleValue();
        showresult.setText(str);
    }
    public void delete()
    {
        str="";
        operator='q';
        num =0.0;
        numtemp=0.0;
        showresult.setText("");
    }
    public void perform()
    {
        str="";
        numtemp = num;
    }
    public void calculate()
    {
        if(operator=='+')
        {
            num = numtemp + num;
        }
        else if(operator=='-')
        {
            num = numtemp - num;
        }
        else if(operator=='*')
        {
            num = numtemp * num;
        }
        else if(operator=='/')
        {
            try
            {
                num = numtemp / num;
            }
            catch (Exception ex)
            {
                System.out.println("Not possible!");
            }
        }
        else if(operator=='x')
        {
            num = Math.pow(numtemp, num);
        }

        showresult.setText(""+num);
    }



    public void btn8Clicked(View view) {
        insert(8);
    }

    public void btn7Clicked(View view) {
        insert(7);
    }

    public void btn9Clicked(View view) {
        insert(9);
    }

    public void btnclearClicked(View view) {
        delete();
    }

    public void btn4Clicked(View view) {
        insert(4);
    }

    public void btn5Clicked(View view) {
        insert(5);
    }

    public void btn6Clicked(View view) {
        insert(6);
    }

    public void btnplusClicked(View view) {
        perform();
        operator='+';
    }

    public void btn1Clicked(View view) {
        insert(1);
    }

    public void btn2Clicked(View view) {
        insert(2);
    }

    public void btn3Clicked(View view) {
        insert(3);
    }

    public void btnminusClicked(View view) {
        perform();
        operator='-';
    }

    public void btnequalClicked(View view) {
        calculate();
    }

    public void btndivideClicked(View view) {
        perform();
        operator='/';
    }

    public void btnmultiClicked(View view) {
        perform();
        operator='*';
    }

    public void btn0Clicked(View view) {
        //insert(0);
        if(showresult.length()>0)
        {
            insert(0);
        }
    }

    public void btn00Clicked(View view) {
        if(showresult.length()>0)
        {
            insert(0);
            insert(0);
        }
    }

    public void btnbackspace(View view) {
        if(str.length()>=1)
        {
            str = str.substring(0, str.length()-1);
            showresult.setText(str);
        }
        else if(str.length()==0)
        {
            showresult.setText("");
        }
    }

    public void btndot(View view) {
        str = str+String.valueOf(".");
        num = Double.valueOf(str).doubleValue();
        showresult.setText(".");
    }

    public void btnsquare(View view) {
        double a = Math.pow(num, 2);
        showresult.setText(""+a);
    }

    public void btnxcapy(View view) {
        perform();
        operator='x';
    }

    public void btncos(View view) {
        double a = Math.cos(num);
        showresult.setText(""+a);
    }
}



