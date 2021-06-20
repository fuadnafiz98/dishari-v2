package org.tensorflow.lite.examples.detection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Features extends AppCompatActivity implements View.OnClickListener {

    String TAG = "Location_API";
    public CardView card_help, card_object, card_notify, card_distance;

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
//                        startActivity(new Intent(getApplicationContext(), Help.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.features:
                        return true;
                }
                return false;
            }
        });

        card_help = (CardView) findViewById(R.id.help_card);
        card_object = (CardView) findViewById(R.id.objectDetection_card);
        card_notify = (CardView) findViewById(R.id.obstacle_card);
        card_distance = (CardView) findViewById(R.id.distance_card);

        card_help.setOnClickListener(this);
        card_object.setOnClickListener(this);
        card_notify.setOnClickListener(this);
        card_distance.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.help_card:
                startActivity(new Intent(getApplicationContext(), Help.class));
                break;
            case R.id.objectDetection_card:
                startActivity(new Intent(getApplicationContext(), DetectorActivity.class));
                break;
            case R.id.obstacle_card:
                startActivity(new Intent(getApplicationContext(), activity_notifyObstacle.class));
                break;
            case R.id.distance_card:
//                startActivity(new Intent(getApplicationContext(),activity_distance_measurement.class));
                break;
        }
    }
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
                    this,
                    "Volume up key pressed",
                    Toast.LENGTH_LONG).show();
        }
        return super.onKeyDown(keyCode,event);
    }
    public void sendSMS(){
        String phoneNo = "01537124724";
//        String phoneNo = "01769550066";
//            String phoneNo = "01678054074";
//         call API here...
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<LocationModel> call = methods.getLocation();

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
                    smsManager.sendTextMessage(phoneNo,null,SMS,null,null);
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

}
