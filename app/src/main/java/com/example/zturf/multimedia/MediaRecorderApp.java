package com.example.zturf.multimedia;

import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.zturf.R;
import java.io.File;

public class MediaRecorderApp extends AppCompatActivity  {
    Button play , record , stop , pause , resume;
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_recorder);
        play = findViewById(R.id.play_btn);
        record = findViewById(R.id.record_btn);
        stop = findViewById(R.id.stop_btn);
        pause = findViewById(R.id.pause_btn);
        resume  = findViewById(R.id.res_btn);

        play.setOnClickListener(v->{

            try{
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(Helper());
                mediaPlayer.prepare();
                mediaPlayer.start();
            }
            catch (Exception e){
                e.printStackTrace();
            }


        });

        pause.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mediaRecorder.pause();
                Toast.makeText(this, "Recording Paused", Toast.LENGTH_SHORT).show();
            }
        });

        resume.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mediaRecorder.resume();
                Toast.makeText(this, "Recording Resumed", Toast.LENGTH_SHORT).show();
            }
        });


        record.setOnClickListener(v->{
            try{
                mediaRecorder = new MediaRecorder();
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                mediaRecorder.setOutputFile(Helper());
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                mediaRecorder.prepare();
                mediaRecorder.start();



                Toast.makeText(this, "Recording is Started", Toast.LENGTH_SHORT).show();
            }
            catch (Exception e){
                e.printStackTrace();
            }

        });

        stop.setOnClickListener(v->{
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            Toast.makeText(this, "Recording Stopped", Toast.LENGTH_SHORT).show();
        });
    }


    private  String Helper(){
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File music = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File file = new File(music,"record" + ".mp3");
        return  file.getPath();
    }

}