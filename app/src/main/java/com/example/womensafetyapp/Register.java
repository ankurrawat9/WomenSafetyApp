package com.example.womensafetyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity {
Button b1,b2,b3,b4;
EditText e1;
ListView listview;
    SQLiteOpenHelper s1;
    SQLiteDatabase sqlitedb;
    DatabaseHandler myDB;
    String name,number,sr,add,email;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    e1=findViewById(R.id.e1);

    listview=findViewById(R.id.l1);
    b1=findViewById(R.id.button1);
    b2=findViewById(R.id.button2);
    b3=findViewById(R.id.button3);

    myDB = new DatabaseHandler(this);

    b1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            String sr=e1.getText().toString();


            AddData(sr);
            Toast.makeText(Register.this, "Added in Database_", Toast.LENGTH_SHORT).show();
            e1.setText("");

        }
    });


    b3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sqlitedb=myDB.getWritableDatabase();
            String x=e1.getText().toString();
            DeleteData(x);
            Toast.makeText(getApplicationContext(), " Number Deleted From Database ", Toast.LENGTH_SHORT).show();
        }

        public boolean DeleteData(String x) {

            return sqlitedb.delete(DatabaseHandler.TABLE_NAME,DatabaseHandler.COL2 +"=?" ,new String[]{x})>0;

        }
    });


    b2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            loadData();
        }
    });



}

    private void loadData() {

        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.getListContents();
        if(data.getCount() == 0){
            Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                theList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                listview.setAdapter(listAdapter);
            }
        }


}


    public void AddData(String newEntry) {

        boolean insertData = myDB.addData(newEntry);

        if(insertData==true){
            Toast.makeText(this, "Data Successfully Inserted!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Something went wrong :(.", Toast.LENGTH_LONG).show();
        }
    }


    }



