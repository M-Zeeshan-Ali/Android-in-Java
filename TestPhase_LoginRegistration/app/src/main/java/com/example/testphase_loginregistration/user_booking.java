package com.example.testphase_loginregistration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class user_booking extends Fragment {
    RecyclerView recyclerView;
    List<book_class> bus_classes;
/*
@Nullable
    @org.jetbrains.annotations.Nullable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_booking);
    }
*/
// public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState)
    @Nullable
    @Override                       //@NotNull
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
                                                    //user_booking
        View view=layoutInflater.inflate(R.layout.activity_user_booking,null,true);

        recyclerView=view.findViewById(R.id.recycleview);
        bus_classes=new ArrayList<>();
        Log.d("","Coming");

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
       //method created
        view1();


        return view;
    }
//here is the method
     void view1() {
        Log.d("Here",""+linkapi.url+"view_user_appoint.php?id="+User_Login.passemail+"");
         StringRequest stringRequest=new StringRequest(Request.Method.GET, linkapi.url + linkapi.url + "view_user_appoint.php?id=" + User_Login.passemail + "",
                 new Response.Listener<String>() {
                     @Override
                     public void onResponse(String response) {
                         try{
                             JSONArray jsonArray=new JSONArray(response);
                             for(int i=0;i< jsonArray.length();i++){
                                 JSONObject jsonObject= jsonArray.getJSONObject(i);
                                 bus_classes.add(new book_class(
                                    jsonObject.getInt("booking_id"),
                                    jsonObject.getString("uid"),
                                    jsonObject.getString("bname"),
                                    jsonObject.getString("start_route"),
                                    jsonObject.getString("end_route"),
                                    jsonObject.getString("bdate")
                                 ));
                                 book_adapter_user adapter=new book_adapter_user(getContext(),bus_classes);
                                 //book_adapter_user adapter=new book_adapter_user(getContext(),bus_classes);
                                 recyclerView.setAdapter(adapter);
                             }
                         }
                         catch (Exception ex){
                             Log.d("",""+ex);
                         }

                     }
                 }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 error.printStackTrace();
             }
         });
         Volley.newRequestQueue(getContext()).add(stringRequest);



    }
}