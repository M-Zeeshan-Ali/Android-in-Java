package com.example.humanagecalculator;
/*
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

 */
//package com.example.agecalculation_fahadhussain;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.app.*;
import android.view.*;
import android.widget.*;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText edittext1;
    private EditText edittext2;
    private Button button2;
    private ImageView imageview1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize()
    {
        edittext1 = (EditText) findViewById(R.id.edittext1);
        imageview1 = (ImageView) findViewById(R.id.imageview1);
        edittext2 = (EditText) findViewById(R.id.edittext2);
        button2 = (Button) findViewById(R.id.button2);


        imageview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(imageview1);
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edittext1.setText("");
                edittext2.setText("");
            }
        });

    }


    public void showDatePickerDialog(View v)
    {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    // Define a DialogFragment class DatePickerFragment.
    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        // Define a new Calendar for current date
        Calendar now = Calendar.getInstance();
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Create DatePickerFragment (a DialogFragment) with a new DatePickerDialog
            int y = now.get(Calendar.YEAR);
            int m = now.get(Calendar.MONTH);
            int d = now.get(Calendar.DAY_OF_MONTH);
// Current day of month, month, and year are set as the day, month, and year of this DatePickerDialog.
            return new DatePickerDialog(getActivity(), this, y, m, d);
        }
        // When Date of birth is selected
        @RequiresApi(api = Build.VERSION_CODES.M)
        public void onDateSet(DatePicker view, int year, int month, int day)
        {
            int mon = month +1;
            // Define a new Calendar for birth date.
            Calendar birthDay = Calendar.getInstance();
            String date = day + "/" + mon + "/" + year;
            // Define the 2 EditText again using their IDs in main.xml.
            EditText edittext21 = getActivity().findViewById(R.id.edittext1);
            EditText edittext22 = getActivity().findViewById(R.id.edittext2);
            edittext21.setText(date);
            // Set the selected year, month, and day as the year, month, and day of Calendar birthDay.
            birthDay.set(Calendar.YEAR, year);
            birthDay.set(Calendar.MONTH, month);
            birthDay.set(Calendar.DAY_OF_MONTH, day);
            // find difference between present date and selected date in milliseconds.
            double diff = (long)(now.getTimeInMillis() - birthDay.getTimeInMillis());




            // If difference is less than 0, show message that selected date is in future.
            if (diff < 0) {
                Toast.makeText(getContext(), "Selected date is in future.", Toast.LENGTH_SHORT)
                        .show();
                edittext22.setText("");
            }
            else
            {
                // Get difference between years
                int years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
                int currMonth = now.get(Calendar.MONTH) + 1;
                int birthMonth = birthDay.get(Calendar.MONTH) + 1;

                // Get difference between months
                int months = currMonth - birthMonth;

                // If month difference is negative then reduce years by one
                // and calculate the number of months.
                if (months < 0){
                    years--;
                    months = 12 - birthMonth + currMonth;
                    if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
                        months--;
                } else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
                {
                    years--;
                    months = 11;
                }

                // Calculate the days
                int days = 0;
                if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
                    days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
                else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
                {
                    int today = now.get(Calendar.DAY_OF_MONTH);
                    now.add(Calendar.MONTH, -1);
                    days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
                }
                else
                {
                    days = 0;
                    if (months == 12)
                    {
                        years++;
                        months = 0;
                    }
                }

                edittext22.setText(years + " years, " + months + " months, " + days + " days");
            }
        }

    }

}
