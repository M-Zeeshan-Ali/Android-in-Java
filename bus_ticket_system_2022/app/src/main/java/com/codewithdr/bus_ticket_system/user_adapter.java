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

public class user_adapter extends RecyclerView.Adapter<user_adapter.ViewHolder> {

    Context mctx;
    List<user_class> user_classes;
//constructor of user_adapter ,Alt+Insert command
    user_adapter(Context mctx, List<user_class> user_classes)
    {
        this.mctx=mctx;
        this.user_classes = user_classes;
    }
//onCreateViewHolder method createds ,ctrl+o
    @NonNull
    @Override
    public user_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(mctx);
        View view=layoutInflater.inflate(R.layout.layout_user_detail,null);

        return new user_adapter.ViewHolder(view);
    }
//onBindViewHolder method create ,Ctrl+o (o,not zero)
    @Override
    public void onBindViewHolder(@NonNull user_adapter.ViewHolder holder, int position) {
        final user_class userlass= user_classes.get(position);

        holder.name.setText(userlass.getName());
        holder.phone.setText(userlass.getPhone());
        holder.cnic.setText(userlass.getCnic());
        if(Integer.parseInt(userlass.getStatus())==0)
        {
            holder.accept.setVisibility(View.VISIBLE);
            holder.reject.setVisibility(View.INVISIBLE);

        }
        else
        {
            holder.accept.setVisibility(View.INVISIBLE);
            holder.reject.setVisibility(View.VISIBLE);


        }

        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("",""+linkapi.url + "accept.php?id="+userlass.getId()+"");
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                        Request.Method.GET, linkapi.url + "accept.php?id="+userlass.getId()+"", null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {


                                    String res = response.getString("success");
                                    if(res.equals("true"))
                                    {
                                        holder.accept.setVisibility(View.INVISIBLE);
                                        holder.reject.setVisibility(View.VISIBLE);

                                        Intent intent = new Intent(mctx, userdetails.class);
                                    mctx.startActivity(intent);
                                    }
                                    else
                                        {
                                            Toast.makeText(mctx,"Some Issue",Toast.LENGTH_LONG).show();
                                        }
                                }
                                catch (Exception exe){
                                    exe.printStackTrace();
                                }
                            }

                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                error.printStackTrace();
                            }
                        }
                );
                Volley.newRequestQueue(mctx).add(jsonObjectRequest);
            }
        });
        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("",""+linkapi.url + "reject.php?id="+userlass.getId()+"");
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                        Request.Method.GET, linkapi.url + "reject.php?id="+userlass.getId()+"", null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {


                                    String res = response.getString("success");
                                    if(res.equals("true"))
                                    {
                                        holder.accept.setVisibility(View.VISIBLE);

                                        holder.reject.setVisibility(View.INVISIBLE);


                                        Intent intent = new Intent(mctx, userdetails.class);
                                        mctx.startActivity(intent);
                                    }
                                    else
                                        {
                                            Toast.makeText(mctx,"Some Issue",Toast.LENGTH_LONG).show();
                                        }
                                }
                                catch (Exception exe){
                                    exe.printStackTrace();
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                error.printStackTrace();
                            }
                        }
                );
                Volley.newRequestQueue(mctx).add(jsonObjectRequest);
            }
        });


    }

    @Override
    public int getItemCount() {
        return user_classes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,phone,cnic;
    Button accept,reject;
    //ctrl+o make a ViewHolder method
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            name=itemView.findViewById(R.id.name);
            phone=itemView.findViewById(R.id.phone);
            cnic=itemView.findViewById(R.id.cnic);

            accept=itemView.findViewById(R.id.accept);
            reject=itemView.findViewById(R.id.reject);




        }
    }
}

