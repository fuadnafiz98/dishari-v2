package org.tensorflow.lite.examples.detection;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MAINACTIVITY";

    public MainActivity() throws FileNotFoundException {
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    sendSMS();
                } else {
                    requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
                }
            }
            Toast.makeText(
                    MainActivity.this,
                    "Volume up key pressed",
                    Toast.LENGTH_LONG).show();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void sendSMS() {
        String phoneNo = "01769550066";
        String SMS = "Chacha Apon pran Bacha";
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, SMS, null, null);
            Toast.makeText(this, "success", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "failed", Toast.LENGTH_LONG).show();
        }
    }

    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.startBtn);
        final ConstraintLayout mConstraintLayout = findViewById(R.id.main_layout);

        mConstraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Features.class);
                startActivity(intent);
                return true;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Features.class);
                startActivity(intent);
            }
        });
    }

    File file = new File("/data/user/0/org.tensorflow.lite.examples.detection/files/contact.txt");

    FileInputStream fis = new FileInputStream(file);
    String[] arr;
//    File notExist = new File("xyz.txt");

//    {
//        try {
//            Log.v("row", file.getCanonicalPath() + " exists? " + file.exists());
//            Log.v("row1", notExist.getCanonicalPath() + " exists? " + notExist.exists());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    {
        try {

            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text1;
            while ((text1 = br.readLine()) != null) {
                sb.append(text1).append(" ");
            }
            String[] arr = sb.toString().split(" ");
            Log.v("number", arr[0]);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}








