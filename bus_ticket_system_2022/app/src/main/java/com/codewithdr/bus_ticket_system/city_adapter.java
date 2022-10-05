package com.codewithdr.bus_ticket_system;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class city_adapter extends RecyclerView.Adapter<city_adapter.ViewHolder> {

    Context mctx;
    List<city_class> city_classes;
//constructor of city Adapter ,Alt+Insert
    city_adapter(Context mctx, List<city_class> city_classes)
    {
        this.mctx=mctx;
        this.city_classes = city_classes;
    }
//onCreateViewHolder method created ,Ctrl+o
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(mctx);
        View view=layoutInflater.inflate(R.layout.layout_city_name,null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final city_class cityClass= city_classes.get(position);

        holder.city.setText(cityClass.getCity());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("",""+linkapi.url + "del_city.php?name="+cityClass.getCity()+"");
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, linkapi.url + "del_city.php?name="+cityClass.getCity()+"", null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try
                                {
                                    String res=response.getString("success");
                                    if(res.equals("true"))
                                    {

                                        Intent intent=new Intent(mctx,citydetail.class);
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
        return city_classes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView city;
        AppCompatButton delete;
        //make a method of ViewHolder ,ctrl+o
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            city=itemView.findViewById(R.id.city);
            delete=itemView.findViewById(R.id.delete);

        }
    }
}
