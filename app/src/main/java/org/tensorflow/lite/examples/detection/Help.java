package org.tensorflow.lite.examples.detection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.ClipData;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.tensorflow.lite.examples.detection.Features;

public class Help extends AppCompatActivity implements View.OnClickListener{
    private CardView notifyObsCard;
    public boolean playing = false;

    //MediaPlayer newMediaPlayer;
    ImageView playIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

/*new approach with toggle */
        final MediaPlayer newMediaPlayer = MediaPlayer.create(this, R.raw.demo_google);
playIcon = findViewById(R.id.obj_dict_media_play);
playIcon.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(!newMediaPlayer.isPlaying()){
            newMediaPlayer.start();
            playIcon.setImageResource(R.drawable.pause);
        }else{
            newMediaPlayer.pause();
            playIcon.setImageResource(R.drawable.play_button);
        }
    }
});



        /*first tutorial*/
//
//        final MediaPlayer objDetectMP = MediaPlayer.create(this, R.raw.demo_google);
//        FloatingActionButton playObjDetect = (FloatingActionButton) this.findViewById(R.id.floatingObjDetect);
//        playObjDetect.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                if(playing == false) {
//                    objDetectMP.start();
//                    playing = true;
//                } else {
//                    objDetectMP.stop();
//                    playing = false;
//                }
//            }
//        });

        /*second tutorial*/
        final MediaPlayer distMeasureMP = MediaPlayer.create(this,R.raw.demo_google);
        FloatingActionButton playDistMeasure = (FloatingActionButton) this.findViewById(R.id.distMeasure);
        playDistMeasure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(playing == false) {
                    distMeasureMP.start();
                    playing = true;
                } else {
                    distMeasureMP.stop();
                    playing = false;
                }
            }
        });

        /*third tutorial*/

        notifyObsCard = (CardView) findViewById(R.id.notObsCard);
        notifyObsCard.setOnClickListener(this);

        /*third tutorial ends*/

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.help);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.help:
                        return true;
                    case R.id.features:
                        startActivity(new Intent(getApplicationContext(), Features.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        final MediaPlayer distMeasureMP = MediaPlayer.create(this,R.raw.demo_google);
        switch (v.getId()){
            case R.id.notObsCard :  distMeasureMP.start(); break;
            default:break;
        }

    }
}