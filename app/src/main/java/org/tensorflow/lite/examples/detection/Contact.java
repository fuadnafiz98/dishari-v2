package org.tensorflow.lite.examples.detection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Contact<fis> extends AppCompatActivity {
    private static final String File_Name = "contact.txt";
    EditText mobile1;
    EditText mobile2;
    EditText mobile3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        mobile1 = findViewById(R.id.mobile1);
        mobile2 = findViewById(R.id.mobile2);
        mobile3 = findViewById(R.id.mobile3);
    }
    public void save(View v){
        String text = mobile1.getText().toString() +"\n" + mobile2.getText().toString() +"\n"+ mobile3.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(File_Name,MODE_PRIVATE);
            fos.write(text.getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fos!= null){
                try{
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    FileInputStream fis = null;


    {
        try {
            fis = openFileInput(File_Name);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String ret_text;
            while ((ret_text = br.readLine()) != null) {
                sb.append(ret_text).append(" ");

            }
            String[] arrSplit = ret_text.split(" ");
            mobile1.setText(arrSplit[0].toString());
            mobile2.setText(arrSplit[1].toString());
            mobile3.setText(arrSplit[2].toString());

            Log.v("check",ret_text);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }finally{
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }
    }




}