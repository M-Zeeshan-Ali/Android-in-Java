package com.example.testphase_loginregistration;

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

public class bus_adapter_user extends RecyclerView.Adapter<bus_adapter_user.ViewHolder> {

Context mctx;
List<bus_class> bus_classes;

    public bus_adapter_user(Context mctx, List<bus_class> bus_classes) {
        this.mctx = mctx;
        this.bus_classes = bus_classes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(mctx);
        View view=layoutInflater.inflate(R.layout.layout_book_ticket,null);

        return new bus_adapter_user.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final bus_class busClass=bus_classes.get(position);

        holder.city.setText(busClass.getcity());
        holder.bus.setText(busClass.getName());
        holder.stime.setText(busClass.getStime());
        holder.etime.setText(busClass.getEtime());

        holder.bnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            int mYear=0,mMonth=0,mDay=0;
                DatePickerDialog datePickerDialog=new DatePickerDialog(mctx, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Log.d("Here",""+year+""+monthOfYear+""+dayOfMonth);
                        String d=year+"/"+monthOfYear+"/"+dayOfMonth;
                        Log.d("",""+linkapi.url  +"addbooking.php?="+User_Login.passemail+"&bname="+busClass.getName()+"&sr="+user_home.saddress+"&er="+user_home.eaddress+"&date="+d+"");
                        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, linkapi.url + "addbooking.php?=" + User_Login.passemail + "&bname=" + busClass.getName() +
                                "&sr=" + user_home.saddress + "&er=" + user_home.eaddress + "&date=" + d + "", null,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try{
                                            String res= response.getString("success");
                                            if(res.equals("true")){
                                                Intent intent=new Intent(mctx,User_Dashboard.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                mctx.startActivity(intent);
                                            }
                                            else{
                                                Toast.makeText(mctx, "Some Issue", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                        catch(Exception ex){
                                            Log.d("",""+ex);

                                        }

                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        });
                        Volley.newRequestQueue(mctx).add(jsonObjectRequest);

                    }
                },mYear,mMonth,mDay);

                datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        dialog.dismiss();
                    }
                });
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                datePickerDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return bus_classes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView city,bus,stime,etime;
        AppCompatButton bnow;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            city=itemView.findViewById(R.id.city);
            bus=itemView.findViewById(R.id.bus);
            bnow=itemView.findViewById(R.id.bnow);
                stime=itemView.findViewById(R.id.stime);
                etime=itemView.findViewById(R.id.etime);
        }
    }
}
