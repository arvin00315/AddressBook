package com.example.arlan.addressbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by arlan on 7/26/2017.
 */

public class AddContact extends AppCompatActivity {

    DatabaseTable mArvinsHelper = new DatabaseTable(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);

        Button buttonAdd = (Button)findViewById(R.id.add_contact_button);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextName = (EditText)findViewById(R.id.add_contact_name);
                String name = editTextName.getText().toString();
                if (name.equals("")){
                    Toast.makeText(getApplicationContext(), "Name cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                EditText editTextMobile = (EditText)findViewById(R.id.add_contact_mobile);
                int mobile = Integer.parseInt(editTextMobile.getText().toString());

                EditText editTextEmail = (EditText)findViewById(R.id.add_contact_email);
                String email = editTextEmail.getText().toString();

                long id = mArvinsHelper.insertData(name, mobile, email);

                if(id < 0){
                    Toast.makeText(getApplicationContext(), "Unsuccessful", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Data added successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cancel_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case R.id.cancel:
                mainActivityView();
                break;
        }

        return true;
    }



    //  Switch top MainActivity
    public void mainActivityView(){
        Intent mainActivityView = new Intent(AddContact.this, MainActivity.class);
        startActivity(mainActivityView);
    }



    /*public void addContactView(View view) {
        String name = this.mName.getText().toString();
        int mobile = Integer.parseInt(this.mMobile.getText().toString());
        String email = this.mEmail.getText().toString();



        if (id < 0) {
            Log.e("arvinsTag", "Unsuccessful");
        } else {
            Log.i("arvinsTag", "Insert Data Successful");
        }

    }*/

}
