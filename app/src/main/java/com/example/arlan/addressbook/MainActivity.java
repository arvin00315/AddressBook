package com.example.arlan.addressbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    DatabaseTable mArvinsHelper;
    EditText mName;
    EditText mMobile;
    EditText mEmail;
    EditText mSearchByName;
    Button mViewDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //  Main page upon running the apps

        //  Reference all EditText id in single_view.xml to a variables
        mName = (EditText) findViewById(R.id.id_name);
        mMobile = (EditText) findViewById(R.id.id_mobile);
        mEmail = (EditText) findViewById(R.id.id_email);
        mSearchByName = (EditText) findViewById(R.id.id_search_data);

        //  Instantiated to be able to call Methods from DatabaseTable
        mArvinsHelper = new DatabaseTable(this);


        //  Reference TextView id in activity_main.xml to be able to set the OnClickListener
        mViewDB = (Button) findViewById(R.id.id_view_db);

        //  Set the OnClickListener
        mViewDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*=======================================================================
                 * Created an object of intent and pass it when we call the startActivity()
                 * to be able to switch page from current to new page.
                 *
                 * The said new activity must set as well in AndroidManifest.xml for it
                 * to work.
                 *
                 * eg.
                 * <activity android:name=".DatabaseView" android:label="DatabaseView" />
                 *=======================================================================*/
                Intent viewDB = new Intent(MainActivity.this, DatabaseView.class);
                startActivity(viewDB);
            }
        });


    }

    public void addContact(View view) {
        String name = this.mName.getText().toString();
        int mobile = Integer.parseInt(this.mMobile.getText().toString());
        String email = this.mEmail.getText().toString();

        long id = mArvinsHelper.insertData(name, mobile, email);

        if (id < 0) {
            Log.e("arvinsTag", "Unsuccessful");
        } else {
            Log.i("arvinsTag", "Insert Data Successful");
        }

    }

    /*public void viewDatabase(View view){
        String data = mDatabaseOpenHelper.getAllData();
        Log.i("arvinsTag",""+data);
    }*/


    public void getDataByName(View view) {
        String data = mSearchByName.getText().toString();
        String data1 = mArvinsHelper.searchSpecificData(data);
        Log.i("arvinsTag", "" + data1);
    }


}
