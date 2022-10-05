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

public class book_adapter_user extends RecyclerView.Adapter<book_adapter_user.ViewHolder> {

    Context mctx;               //context variable is declared mctx.
    List<book_class> bus_classes;    //list of bus_class variable is declared "bus_class"

    /*bus_adapter variable is declared which is assign context**** ,list of buses ,and classes of buses calling */
    book_adapter_user(Context mctx, List<book_class> bus_classes)
    {
        /*this is used for calling mctx activity and bus_classes activity
        */
        this.mctx=mctx;
        this.bus_classes = bus_classes;
    }
//Data Binding of bus_adapter
    @NonNull
    @Override
    public book_adapter_user.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(mctx);
        View view=layoutInflater.inflate(R.layout.layout_book_view,null);

        return new book_adapter_user.ViewHolder(view);
    }
    //Data Binding of viewholder with the help of adapter class "and show its position where the last bus "
    @Override
    public void onBindViewHolder(@NonNull book_adapter_user.ViewHolder holder, int position) {
        final book_class busClass= bus_classes.get(position);    //get the position of bus

        holder.date.setText(busClass.getDate());        //selection the city where user want to go
        holder.bus.setText(busClass.getBname());//select the bus and find his name
        holder.saddress.setText(busClass.getSaddress());
        holder.eaddress.setText(busClass.getEaddress());



    }
//data binding
    @Override
    public int getItemCount() {
        return bus_classes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bus,saddress,eaddress,date;          //testview class is declared using city and bus variable
        //create constructor of matching super
        //appcompatButton is object to declare the varialbe delete to delete from app and database record
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date=itemView.findViewById(R.id.date);  //city variable is set (itemview.findviewby id and xml assign id "city".
            bus=itemView.findViewById(R.id.bus);    //bus variable is set (itemview.findviewby id and xml assign id "city".
              saddress=itemView.findViewById(R.id.sadd);
                eaddress=itemView.findViewById(R.id.eadd);
        }
    }
}
        //close parentheses
