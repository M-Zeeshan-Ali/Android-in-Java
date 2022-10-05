/*
package com.example.testphase_loginregistration;

public class book_adapter_user {
}
*/
package com.example.testphase_loginregistration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class book_adapter_user extends RecyclerView.Adapter<book_adapter_user.ViewHolder> {
    Context mctx;
    List<book_class> bus_classes;

    book_adapter_user(Context mctx, List<book_class> bus_classes) {
        this.mctx = mctx;
        this.bus_classes = bus_classes;
    }

    @NonNull
    @Override
    public book_adapter_user.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(mctx);
        View view=layoutInflater.inflate (R.layout.layout_book_view,null);
        return new book_adapter_user.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull book_adapter_user.ViewHolder holder, int position) {
        final book_class busClass=bus_classes.get(position);

        holder.date.setText(busClass.getDate());
        holder.bus.setText(busClass.getBname());
        holder.saddress.setText(busClass.getSaddress());
        holder.eadress.setText(busClass.getEaddress());
        //super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemCount() {
        return bus_classes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bus,saddress,eadress,date;


        public ViewHolder(@NonNull View itemView) {


            super(itemView);

            date=itemView.findViewById(R.id.date);
            bus=itemView.findViewById(R.id.bus);
            saddress=itemView.findViewById(R.id.sadd);
            eadress=itemView.findViewById(R.id.eadd);

        }
    }



}
