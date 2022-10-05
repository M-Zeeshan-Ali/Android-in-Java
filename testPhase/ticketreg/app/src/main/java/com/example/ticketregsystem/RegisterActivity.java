package com.example.ticketregsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ticketregsystem.Connection.ConnectionClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class RegisterActivity extends AppCompatActivity {

    EditText firstName, lastName, userName, password, address, contactNo, gender;
    Button register;
    TextView status;

    Connection conn;
    Statement stmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstName = (EditText)findViewById(R.id.firstName);
        lastName = (EditText)findViewById(R.id.lastName);
        userName = (EditText)findViewById(R.id.userName);
        password = (EditText)findViewById(R.id.password);
        address = (EditText)findViewById(R.id.address);
        contactNo = (EditText)findViewById(R.id.contactNo);
        gender = (EditText)findViewById(R.id.gender);
        register = (Button)findViewById(R.id.register);
        status = (TextView)findViewById(R.id.status);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RegisterActivity.registeruser().execute("");
            }
        });



    }

    public class registeruser extends AsyncTask<String, String, String> {

        // to check weather connection
        String z = "";
        //assigning false randomly to bool to check
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute() {
            status.setText("sending data to database");

        }

        @Override
        protected void onPostExecute(String s) {
            status.setText("Registered and Sign up Successfully!");
            firstName.setText("");
            lastName.setText("");
            userName.setText("");
            address.setText("");
            contactNo.setText("");
            gender.setText("");

        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                conn = connectionClass(ConnectionClass.un.toString(), ConnectionClass.pass.toString(), ConnectionClass.db.toString(), ConnectionClass.ip.toString());
                if (conn == null) z = "Check Your Internet Connection";
                else {
                    String sql = "INSERT INTO registeration (firstName, lastName, address, contact_no, gender) VALUES ('"+firstName.getText()+"','"+lastName.getText()+"','"+address.getText()+"',"+contactNo.getText()+"','"+gender.getText()+"')";
                    //  String sql1 = ""
                    stmt = conn.createStatement();
                    stmt.executeUpdate(sql);

                }
            } catch (Exception e) {
                isSuccess = false;
                z = e.getMessage();
            }


            return z;
        }

        // Get Database connection

        @SuppressLint("New Api")
        public Connection connectionClass(String user, String password, String database, String server) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Connection connection = null;
            String connectionURL = null;

            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                connectionURL = "jdbc:jtds:sqlserver://" + server+"/" + database + ";user=" + user + ";password=" + password + ";";
                connection = DriverManager.getConnection(connectionURL);
            } catch (Exception e) {
                Log.e("SQL Connection Error : ", e.getMessage());
            }
            return connection;
        }

    }

    public static class LoginActivity {
    }
}
