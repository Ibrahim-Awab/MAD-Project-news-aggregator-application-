package com.example.midterm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import java.util.List;

public class Adapter extends ArrayAdapter<NewsF>
{
    private int MyresourceLayout;
    private Context MyContext;
    private List<NewsF>itemList;

    public Adapter(Context context, int resource, List<NewsF> item)
    {
        super(context, resource, item);
        this.MyresourceLayout = resource;
        this.itemList=item;
        this.MyContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View see = convertView;

        if (see == null)
        {
            LayoutInflater vie;
            vie = LayoutInflater.from(MyContext);
            see = vie.inflate(MyresourceLayout, null);
        }

        NewsF value = getItem(position);

        TextView appHeading=see.findViewById(R.id.app_heading);
        TextView appDesc=see.findViewById(R.id.app_desc);
        TextView appUrl=see.findViewById(R.id.app_url);
        TextView appID=see.findViewById(R.id.app_id);
        ImageView appimg=see.findViewById(R.id.app_img_view);

        appHeading.setText("Heading: "+value.getHeading());
        appDesc.setText("Description: "+value.getDescription());
        appUrl.setText("URL:"+value.getUrl());
        appID.setText("ID:" +String.valueOf(value.getId()));
        Glide.with(MyContext).load(value.getUrl()).into(appimg);

        return see;
    }

}
