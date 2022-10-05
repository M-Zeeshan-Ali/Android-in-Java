package com.codewithdr.bus_ticket_system;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.List;
/*Bus_adapter class is call of publicily,adapter is a helper to view two thing "viewHolder is used to view the "
androidx.appcompat.widget.AppCompatSpinner is a standard library is used on xml design file */

public class bus_adapter_user extends RecyclerView.Adapter<bus_adapter_user.ViewHolder> {

    Context mctx;               //context variable is declared mctx.
    List<bus_class> bus_classes;    //list of bus_class variable is declared "bus_class"

    /*bus_adapter variable is declared which is assign context**** ,list of buses ,and classes of buses calling */
    bus_adapter_user(Context mctx, List<bus_class> bus_classes)
    {
        /*this is used for calling mctx activity and bus_classes activity
        */
        this.mctx=mctx;
        this.bus_classes = bus_classes;
    }
//Data Binding of bus_adapter
    @NonNull
    @Override
    public bus_adapter_user.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(mctx);
        View view=layoutInflater.inflate(R.layout.layout_book_ticket,null);

        return new bus_adapter_user.ViewHolder(view);
    }
    //Data Binding of viewholder with the help of adapter class "and show its position where the last bus "
    @Override
    public void onBindViewHolder(@NonNull bus_adapter_user.ViewHolder holder, int position) {
        final bus_class busClass= bus_classes.get(position);    //get the position of bus

        holder.city.setText(busClass.getCity());        //selection the city where user want to go
        holder.bus.setText(busClass.getName());//select the bus and find his name
        holder.stime.setText(busClass.getStime());
        holder.etime.setText(busClass.getEtime());
        //delete the selected city and click listener class is call
        holder.bnow.setOnClickListener(new View.OnClickListener() {
            @Override           // delete the debugger to use l(og.d) also delete data from database where we stored,and show delete_bus
            public void onClick(View v) {
                int mYear=0,mMonth=0,mDay=0;
                DatePickerDialog datePickerDialog = new DatePickerDialog(mctx, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        //here you go
                        Log.d("Here ",""+year+""+monthOfYear+""+dayOfMonth);
                        String d=year+"/"+monthOfYear+"/"+dayOfMonth;
                        Log.d("",""+linkapi.url + "addbooking.php?email="+user_login.passemail+"&bname="+busClass.getName()+"&sr="+user_home.saddress+"&er="+user_home.eaddress+"&date="+d+"");
                        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, linkapi.url + "addbooking.php?email="+user_login.passemail+"&bname="+busClass.getName()+"&sr="+user_home.saddress+"&er="+user_home.eaddress+"&date="+d+"", null,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try
                                        {
                                            String res=response.getString("success");  //string class is call to show the message "success"
                                            if(res.equals("true"))          //if condition is true then intent class declared
                                            {
                                                //intent class is used to move one activity to next activity
                                                Intent intent=new Intent(mctx,user_dashboard.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                mctx.startActivity(intent);


                                            } /*else condition ,toast.makeText is used to the show message "some issue",length_long is hold the message.show is used to show on screen*/
                                            else{
                                                Toast.makeText(mctx,"Some Issue",Toast.LENGTH_LONG).show();
                                            }
                                        }
                                        catch (Exception ex)            //otherwise error message show if ,dual condition is not executed
                                        {
                                            Log.d("Error",""+ex);
                                        }

                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override           //volley library is used,to communication B/W database and code to provide a path or app through volley library
                                    public void onErrorResponse(VolleyError error) {
                                        error.printStackTrace();
                                    }
                                });
                        Volley.newRequestQueue(mctx).add(jsonObjectRequest);
                    }
                }, mYear, mMonth, mDay);

                datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {

                        dialog.dismiss();
                    }
                });
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();

            }
        });


    }
//data binding
    @Override
    public int getItemCount() {
        return bus_classes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView city,bus,stime,etime;          //testview class is declared using city and bus variable
        AppCompatButton bnow;     //appcompatButton is object to declare the varialbe delete to delete from app and database record
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            city=itemView.findViewById(R.id.city);  //city variable is set (itemview.findviewby id and xml assign id "city".
            bus=itemView.findViewById(R.id.bus);    //bus variable is set (itemview.findviewby id and xml assign id "city".
            bnow=itemView.findViewById(R.id.bnow);  //delete variable is set (itemview.findviewby id and xml assign id "delete".
                stime=itemView.findViewById(R.id.stime);
                etime=itemView.findViewById(R.id.etime);
        }
    }
}
        //close parentheses
