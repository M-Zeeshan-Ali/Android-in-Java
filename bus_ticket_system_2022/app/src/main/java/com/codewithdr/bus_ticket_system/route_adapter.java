package com.codewithdr.bus_ticket_system;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class route_adapter extends RecyclerView.Adapter<route_adapter.ViewHolder> {

    Context mctx;
    List<route_class> route_classes;

    route_adapter(Context mctx, List<route_class> route_classes)
    {
        this.mctx=mctx;
        this.route_classes = route_classes;
    }

    @NonNull
    @Override
    public route_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(mctx);
        View view=layoutInflater.inflate(R.layout.layout_route_name,null);

        return new route_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull route_adapter.ViewHolder holder, int position) {
        final route_class routeClass= route_classes.get(position);

        holder.city.setText(routeClass.getCname());
        holder.bus.setText(routeClass.getBname());
        holder.route.setText(routeClass.getName());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("",""+linkapi.url + "del_route.php?name="+routeClass.getName()+"");
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, linkapi.url + "del_route.php?name="+routeClass.getName()+"", null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try
                                {
                                    String res=response.getString("success");
                                    if(res.equals("true"))
                                    {

                                        Intent intent=new Intent(mctx,routedetail.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        mctx.startActivity(intent);


                                    }
                                    else{
                                        Toast.makeText(mctx,"Some Issue",Toast.LENGTH_LONG).show();
                                    }
                                }
                                catch (Exception ex)
                                {
                                    Log.d("Error",""+ex);
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        });
                Volley.newRequestQueue(mctx).add(jsonObjectRequest);
            }
        });


    }

    @Override
    public int getItemCount() {
        return route_classes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView city,bus,route;
        AppCompatButton delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            route=itemView.findViewById(R.id.route);
            city=itemView.findViewById(R.id.city);
            bus=itemView.findViewById(R.id.bus);
            delete=itemView.findViewById(R.id.delete);

        }
    }
}

