package com.example.arlan.addressbook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by arlan on 7/16/2017.
 */

public class RecyclerAdapter extends ArrayAdapter<Data> {



    public RecyclerAdapter(@NonNull Context context, @NonNull ArrayList<Data> objects) {
        super(context, 0, objects);
    }


    /*===================================================================================
    *  This function will keep on executing while scrolling the list.
    *====================================================================================*/

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.single_view, parent, false);
        }


            Data currentData = getItem(position); //  used to fetch data at the specified position

            TextView name = (TextView)listItemView.findViewById(R.id.name);
            name.setText(currentData.getName());

            TextView mobile = (TextView)listItemView.findViewById(R.id.mobile);
            mobile.setText(""+currentData.getMobile());

            TextView email = (TextView)listItemView.findViewById(R.id.email);
            email.setText(currentData.getEmail());

        return listItemView;
    }
}
