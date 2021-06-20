package org.tensorflow.lite.examples.detection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Contact<fis> extends AppCompatActivity {
    public static final String File_Name = "contact.txt";
   public EditText mobile1;
   public  EditText mobile2;
   public EditText mobile3;


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


            Log.v("file","sAVED TO"+ getFilesDir() + "/"+ File_Name );
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

    public void load(View v){
        FileInputStream fis = null;
        try{
            fis = openFileInput(File_Name);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text1;
            while ((text1 = br.readLine()) != null){
                sb.append(text1).append(" ");
            }
            String[] arr = sb.toString().split(" ");
            mobile1.setText(arr[0]);
            mobile2.setText(arr[1]);
            mobile3.setText(arr[2]);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fis != null){
                try{
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}