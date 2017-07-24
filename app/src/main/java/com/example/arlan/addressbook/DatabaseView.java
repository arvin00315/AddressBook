package com.example.arlan.addressbook;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by arlan on 7/16/2017.
 */

public class DatabaseView extends AppCompatActivity {
    DatabaseTable mArvinsHelper = new DatabaseTable(this);
    ArrayList<Data> mArrayData = new DatabaseTable(this).mArrayData;
    EditText mSearch;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        mSearch = (EditText)findViewById(R.id.search_contact);
        String data = mSearch.getText().toString();
        mArvinsHelper.searchSpecificData(data);
        

        mArrayData = mArvinsHelper.getAllData();
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this, mArrayData);

        ListView listView = (ListView)findViewById(R.id.list_item_db);
        listView.setAdapter(recyclerAdapter);

    }
}
