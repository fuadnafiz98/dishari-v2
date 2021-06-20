package org.tensorflow.lite.examples.detection;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_distance_measurement extends AppCompatActivity {

    private static final String TAG = "activity_distance_measurementz";
    private Button getData;
    private RecyclerView recyclerView;


    private ArrayList<Data> arrayList;
    private TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_measurement);
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if(result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS_DISTANCE", "Lang not supported");
                    } else {

                    }
                } else {
                    Log.e("TTS_DISTANCE", "Lang not supported");
                }
            }
        });


        getData = findViewById(R.id.getData);
        recyclerView = findViewById(R.id.recyclerViewEx);
        arrayList = new ArrayList<>();

//        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(arrayList);
//        recyclerView.setAdapter(recyclerAdapter);


        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
                Call<Model> call = methods.getAllData();

                call.enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {
                        Log.e(TAG,"onResponse: code :"+ response.code());
                        double data = response.body().getDistance();
                        speak("Object is " + (int)data+" cm away.");
//                        for(Model data1: data){
//                            Log.e(TAG, "onResponse: All emails: "+data1.getEmail());
                        arrayList.add(new Data(data));
//                        }

                        //getting data to represent in app
                        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(arrayList);
                        recyclerView.setAdapter(recyclerAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(activity_distance_measurement.this));
                        //finish

                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {
                        Log.e(TAG, "onResponse: "+t.getMessage());
                    }
                });
            }
        });
    }
    private void speak(String distance) {
        tts.speak(distance, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onDestroy() {
        if(tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}