package com.assignment.adda247;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class adapter extends ArrayAdapter<Data> {

    //the data list that will be displayed
    private List<Data> dataList;

    //the context object
    private Context mCtx;

    //here we are getting the datalist and context
    //so while creating the object of this adapter class we need to give datalist and context
    public adapter(List<Data> dataList, Context mCtx) {
        super(mCtx, R.layout.list_layout, dataList);
        this.dataList = dataList;
        this.mCtx = mCtx;
    }

    //this method will return the list item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        //getting text views
        TextView textViewName = listViewItem.findViewById(R.id.textViewName);
        TextView textViewEmail = listViewItem.findViewById(R.id.textViewEmail);
        TextView textViewGender = listViewItem.findViewById(R.id.textViewGender);
        TextView textViewStatus = listViewItem.findViewById(R.id.textViewStatus);


        //Getting the data for the specified position
        Data data = dataList.get(position);

        //setting data values to textviews
        textViewName.setText("Name: "+data.getName());
        textViewEmail.setText("Email: "+data.getEmail());
        textViewGender.setText("Gender: "+data.getGender());
        textViewStatus.setText("Status: "+data.getStatus());


        //returning the listitem
        return listViewItem;
    }
}