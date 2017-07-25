package com.example.arlan.addressbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    DatabaseTable mArvinsHelper = new DatabaseTable(this);
    EditText mName;
    EditText mMobile;
    EditText mEmail;
    EditText mSearchByName;
    Button mViewDB;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case R.id.add_contact:
                addContact();
                break;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //  Main page upon running the apps

        ArrayList<Data> mArrayData;
        mArrayData = mArvinsHelper.getAllData();
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this, mArrayData);


        ListView listView = (ListView)findViewById(R.id.list_item_db);
        listView.setAdapter(recyclerAdapter);
    }

    /*public void addContact(View view) {
        String name = this.mName.getText().toString();
        int mobile = Integer.parseInt(this.mMobile.getText().toString());
        String email = this.mEmail.getText().toString();

        long id = mArvinsHelper.insertData(name, mobile, email);

        if (id < 0) {
            Log.e("arvinsTag", "Unsuccessful");
        } else {
            Log.i("arvinsTag", "Insert Data Successful");
        }

    }*/

    public void addContact(){
        Log.i("arvinsTag","add contact called");
    }



    public void getDataByName(View view) {
        String data = mSearchByName.getText().toString();
        String data1 = mArvinsHelper.searchSpecificData(data);
        Log.i("arvinsTag", "" + data1);
    }


}
