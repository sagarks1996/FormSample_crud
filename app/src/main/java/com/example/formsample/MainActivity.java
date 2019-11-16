package com.example.formsample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    AppCompatEditText firstname,lastname,newfname,newlname,usrid;
    AppCompatButton addbutton,viewallbutton,viewbutton;
    Dbhelper mydb;


    public void showmessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new Dbhelper(this);

        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        addbutton = findViewById(R.id.addbutton);
        viewallbutton = findViewById(R.id.viewbutton);

        usrid = findViewById(R.id.uid);
        newfname = findViewById(R.id.newfirstname);
        newlname = findViewById(R.id.newlastname);
        viewbutton = findViewById(R.id.view);




        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fname = firstname.getText().toString();
                String lname = lastname.getText().toString();


                if (fname.isEmpty() || lname.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter data",Toast.LENGTH_SHORT).show();


                }else {

                    boolean isinserted = mydb.insertdata(fname,lname);
                    if (isinserted==true){
                        Toast.makeText(getApplicationContext(),"Data inserted",Toast.LENGTH_SHORT).show();
                        firstname.setText("");
                        lastname.setText("");
                    }else {
                        Toast.makeText(getApplicationContext(),"Data not inserted",Toast.LENGTH_SHORT).show();
                        firstname.setText("");
                        lastname.setText("");
                    }

                }

            }
        });


        viewallbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cursor = mydb.getalldata();
                if (cursor.getCount() == 0){
                    showmessage("error","Nothing Found");
                }else {
                    StringBuffer stringBuffer = new StringBuffer();
                    while (cursor.moveToNext()){
                        stringBuffer.append("Id :"+cursor.getString(0)+"\n");
                        stringBuffer.append("Name :"+cursor.getString(1)+" "+cursor.getString(2)+"\n\n");
                    }
                    showmessage("Data",stringBuffer.toString());
                }
            }
        });

        viewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = usrid.getText().toString();
                StringBuffer stringBuffer = new StringBuffer();
                Cursor cursor = mydb.getdata(id);
                if(cursor.moveToNext()){

                    String fname = cursor.getString(1);
                    String lname = cursor.getString(2);

                    newfname.setText(fname);
                    newlname.setText(lname);

//                    stringBuffer.append("Name :"+cursor.getString(1)+" "+cursor.getString(2));
                }
//                showmessage("Data",stringBuffer.toString());


            }
        });





    }
}
