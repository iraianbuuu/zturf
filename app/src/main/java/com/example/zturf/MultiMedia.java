package com.example.zturf;
import com.example.zturf.multimedia.*;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MultiMedia extends AppCompatActivity {
    Button player;
    Button Recorder;
    Button video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_media);
        player = findViewById(R.id.button4);
        Recorder = findViewById(R.id.button3);
        video = findViewById(R.id.video);

        player.setOnClickListener(v ->{
            Intent i = new Intent(this,MediaPlayerApp.class);
            startActivity(i);
        });



        Recorder.setOnClickListener(v->{
            Intent i = new Intent(this,MediaRecorderApp.class);
            startActivity(i);
        });

        video.setOnClickListener(v->{
            Intent i = new Intent(this,VideoRec.class);
            startActivity(i);
        });



    }
}