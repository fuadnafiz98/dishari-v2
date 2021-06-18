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

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MAINACTIVITY";
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_VOLUME_UP){

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                    sendSMS();
                }else{
                    requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
                }
            }
            Toast.makeText(
                    MainActivity.this,
                    "Volume up key pressed",
                    Toast.LENGTH_LONG).show();
        }
        return super.onKeyDown(keyCode,event);
    }
    public void sendSMS(){
        String phoneNo = "01769550066";
        String SMS ="Chacha Apon pran Bacha";
        try{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo,null,SMS,null,null);
            Toast.makeText(this,"success",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this,"failed",Toast.LENGTH_LONG).show();
        }
    }

    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.startBtn);
        final ConstraintLayout mConstraintLayout=findViewById(R.id.main_layout);

        mConstraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Features.class);
                startActivity(intent);
                return  true;
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
}