package com.example.zeeshan_baseadapterpractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class CustomAdapter extends BaseAdapter {
    Context context;
    int images[];
    LayoutInflater inflater;

    public CustomAdapter(Context applicationContext, int[] images) {
        this.context=applicationContext;
        this.images=images;
        inflater=(LayoutInflater.from(applicationContext));
    }


    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

    convertView=inflater.inflate(R.layout.activity_grid_view,null);
        ImageView icon=(ImageView) convertView.findViewById(R.id.icon);
        icon.setImageResource(images[position]);
        return convertView;
    }
}
