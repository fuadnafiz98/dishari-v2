package org.tensorflow.lite.examples.detection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Features extends AppCompatActivity implements View.OnClickListener {

    String TAG = "Location_API";
    public CardView card_help, card_object, card_notify, card_distance, card_obj_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_features);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.help:

                        startActivity(new Intent(getApplicationContext(), Help.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.features:
                        return true;
                }
                return false;
            }
        });

        card_help = (CardView) findViewById(R.id.money_card);
        card_object = (CardView) findViewById(R.id.objectDetection_card);
        card_notify = (CardView) findViewById(R.id.obstacle_card);
        card_distance = (CardView) findViewById(R.id.bookReading_card);

        card_obj_2 = (CardView) findViewById(R.id.objectDetection_card);

        card_help.setOnClickListener(this);
        card_object.setOnClickListener(this);
        card_notify.setOnClickListener(this);
        card_distance.setOnClickListener(this);



//        card_help.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MoneyNoteDetection.class);
//                startActivity(intent);
//            }
//        }
//        );

        final MediaPlayer newMediaPlayer3n = MediaPlayer.create(this, R.raw.money_note_detection);
        card_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!newMediaPlayer3n.isPlaying()){
                    newMediaPlayer3n.start();
                    Intent intent = new Intent(getApplicationContext(), MoneyNoteDetection.class);
                    startActivity(intent);
                    //playIcon2.setImageResource(R.drawable.pause);
                }else{
                    newMediaPlayer3n.pause();
                    //playIcon2.setImageResource(R.drawable.play_button);
                }
            }
        });
        /*new tap option objection detection*/

        final MediaPlayer newMediaPlayer2 = MediaPlayer.create(this, R.raw.object_detect_feat);
        card_obj_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!newMediaPlayer2.isPlaying()){
                    newMediaPlayer2.start();
                    Intent intent = new Intent(getApplicationContext(), DetectorActivity.class);
                    startActivity(intent);
                    //playIcon2.setImageResource(R.drawable.pause);
                }else{
                    newMediaPlayer2.pause();
                    //playIcon2.setImageResource(R.drawable.play_button);
                }
            }
        });


        /*new tap for distance measure */

        final MediaPlayer newMediaPlayer3 = MediaPlayer.create(this, R.raw.book_reading_selected);
        card_distance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!newMediaPlayer3.isPlaying()){
                    newMediaPlayer3.start();
                    Intent intent = new Intent(getApplicationContext(), BookReading.class);
                    startActivity(intent);
                    //playIcon2.setImageResource(R.drawable.pause);
                }else{
                    newMediaPlayer3.pause();
                    //playIcon2.setImageResource(R.drawable.play_button);
                }
            }
        });

        /*new tap for notify obs */

        final MediaPlayer newMediaPlayer = MediaPlayer.create(this, R.raw.not_obs_feat);
        card_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!newMediaPlayer.isPlaying()){
                    newMediaPlayer.start();
                    Intent intent = new Intent(getApplicationContext(), activity_notifyObstacle.class);
                    startActivity(intent);
                    //playIcon2.setImageResource(R.drawable.pause);
                }else{
                    newMediaPlayer.pause();
                    //playIcon2.setImageResource(R.drawable.play_button);
                }
            }
        });


    }
    //final MediaPlayer newMediaPlayer2 = MediaPlayer.create(this, R.raw.object_detect_feat);
//    card_help.setOnClickListener(new View.OnClickListener()
//
//    {
//        @Override
//        public void onClick (View v){
//        Intent intent = new Intent(getApplicationContext(), activity_notifyObstacle.class);
//        startActivity(intent);
//    }
//    }

//    @Override
//    public void onClick(View v) {
//        Intent i;
//        switch (v.getId()) {
//            case R.id.help_card:
//                startActivity(new Intent(getApplicationContext(), MoneyNoteDetection.class));
//                //newMediaPlayer2.start();
//                break;
////            case R.id.objectDetection_card:
////                startActivity(new Intent(getApplicationContext(), DetectorActivity.class));
////                break;
////            case R.id.obstacle_card:
////                startActivity(new Intent(getApplicationContext(), activity_notifyObstacle.class));
////                break;
////            case R.id.distance_card:
////                startActivity(new Intent(getApplicationContext(),activity_distance_measurement.class));
////                break;
//        }
//    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_VOLUME_UP){

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                    try {
                        sendSMS();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }else{
                    requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
                }
            }
            Toast.makeText(
                    this,
                    "Message sent successfully",
                    Toast.LENGTH_LONG).show();
        }
        return super.onKeyDown(keyCode,event);
    }
    public void sendSMS() throws FileNotFoundException {
        String phoneNo = "01537124724";
        String phoneNo1 = "01738319627";
        String phoneNo2 = "01747474730";
        String phoneNo3 = "01537124724";
//        String phoneNo = "01769550066";
//            String phoneNo = "01678054074";
//         call API here...

        File file = new File("/data/user/0/org.tensorflow.lite.examples.detection/files/contact.txt");
        FileInputStream fis = new FileInputStream(file);
        String[] arr;
        if(file.exists())
        {
            try {
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String text1;
                while ((text1 = br.readLine()) != null) {
                    sb.append(text1).append(" ");
                }
                arr = sb.toString().split(" ");
                phoneNo1 = arr[0];
                phoneNo2 = arr[1];
                phoneNo3 = arr[2];
                Log.v("number", arr[0]);
                Log.v("number", arr[1]);
                Log.v("number", arr[2]);
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
        else{ }


        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<LocationModel> call = methods.getLocation();

        String finalPhoneNo1 = phoneNo1;
        String finalPhoneNo2 = phoneNo2;
        String finalPhoneNo3 = phoneNo3;
        call.enqueue(new Callback<LocationModel>() {
            @Override
            public void onResponse(Call<LocationModel> call, Response<LocationModel> response) {
                Log.e(TAG,"onResponse: code :"+ response.code());
                double lat = response.body().getLat();
                double lon = response.body().getLon();
                String link = response.body().getLink();
                String SMS ="Help needed!!! \nMy current Location: \nLAT " + lat + "\nLON " + lon + "\nGoogle Maps Link: \n" + link;
                Log.e(TAG, SMS);
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(finalPhoneNo1,null,SMS,null,null);
                    smsManager.sendTextMessage(finalPhoneNo2,null,SMS,null,null);
                    smsManager.sendTextMessage(finalPhoneNo3,null,SMS,null,null);
                    //Toast.makeText(this,"success",Toast.LENGTH_LONG).show();
                    Log.e(TAG, "message sent");
                } catch (Exception e){
                    e.printStackTrace();
                    //Toast.makeText(this,"failed",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<LocationModel> call, Throwable t) {
                Log.e(TAG, "onResponse: "+t.getMessage());
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
