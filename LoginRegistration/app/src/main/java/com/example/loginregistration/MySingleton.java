package com.example.loginregistration;

import android.content.Context;
import android.net.Network;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MySingleton<mInstance> {
    
    private static MySingleton mInstance;
    private RequestQueue mRequestQueue;
    private Context mCtx;

    //Alt+Insert for making constructor/getter/setter method
    public MySingleton(Context mCtx) {
        this.mCtx = mCtx;
        mRequestQueue=getmRequestQueue();
    }

    public RequestQueue getmRequestQueue() {
        if (mRequestQueue==null){
            Cache cache= new DiskBasedCache(mCtx.getCacheDir(),1024*1024);
            Network network=new BasicNetwork(new HurlStack());
            mRequestQueue=new RequestQueue(cache,network);
            mRequestQueue=Volley.newRequestQueue(mCtx,getApplicationContext());

        }
        return mRequestQueue;
    }
    public static synchronized MySingleton getmInstance(Context context);
    if(mInstance== null){
        mInstance=new MySingleton(context);
    }
    return mInstance;
}
public <T> void addToRequestQueue(Request<T> request){
    mRequestQueue.add(request);
}
}