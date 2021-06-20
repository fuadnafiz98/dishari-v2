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
    private CardView contact;

    public boolean playing = false;

    //MediaPlayer newMediaPlayer;
    ImageView playIcon, playIcon2, playIcon3;

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

//second one

        final MediaPlayer newMediaPlayer2 = MediaPlayer.create(this, R.raw.demo_google);
        playIcon2 = findViewById(R.id.dist_measure_media_play);
        playIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!newMediaPlayer2.isPlaying()){
                    newMediaPlayer2.start();
                    playIcon2.setImageResource(R.drawable.pause);
                }else{
                    newMediaPlayer2.pause();
                    playIcon2.setImageResource(R.drawable.play_button);
                }
            }
        });


        //third one

        final MediaPlayer newMediaPlayer3 = MediaPlayer.create(this, R.raw.demo_google);
        playIcon3 = findViewById(R.id.notify_obs_media_play);
        playIcon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!newMediaPlayer2.isPlaying()){
                    newMediaPlayer2.start();
                    playIcon3.setImageResource(R.drawable.pause);
                }else{
                    newMediaPlayer2.pause();
                    playIcon3.setImageResource(R.drawable.play_button);
                }
            }
        });

        //forth one
        contact = (CardView) findViewById(R.id.contact);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Contact.class));
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
//        final MediaPlayer distMeasureMP = MediaPlayer.create(this,R.raw.demo_google);
//        FloatingActionButton playDistMeasure = (FloatingActionButton) this.findViewById(R.id.distMeasure);
//        playDistMeasure.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(playing == false) {
//                    distMeasureMP.start();
//                    playing = true;
//                } else {
//                    distMeasureMP.stop();
//                    playing = false;
//                }
//            }
//        });

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