package com.example.hideunhidepassword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class PasswordHideUnhide extends AppCompatActivity {

        AppCompatEditText password;
        CheckBox checkBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_hide_unhide);

        checkBtn=findViewById(R.id.checkbox_btn);
        password=findViewById(R.id.password);

        checkBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //for Show Password
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{
                    //For Hide Password
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });




    }
}