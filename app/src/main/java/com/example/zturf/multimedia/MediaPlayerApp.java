package com.example.zturf.multimedia;

import com.example.zturf.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.SeekBar;

public class MediaPlayerApp extends AppCompatActivity {
    FloatingActionButton start, stop, pause;
    SeekBar seekBar;
    MediaPlayer mediaPlayer;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        start = findViewById(R.id.play);
        stop = findViewById(R.id.stop);
        pause = findViewById(R.id.pause);
        seekBar = findViewById(R.id.seekbar);
        mediaPlayer = MediaPlayer.create(this, R.raw.song);
        handler = new Handler();

        start.setOnClickListener(v -> {
            mediaPlayer.start();
            updateSeekBar();
        });

        stop.setOnClickListener(v -> {
            mediaPlayer.stop();
        });

        pause.setOnClickListener(v -> {
            mediaPlayer.pause();
        });

        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    mediaPlayer.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
    private void updateSeekBar() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            seekBar.setProgress(mediaPlayer.getCurrentPosition());
            handler.postDelayed(this::updateSeekBar, 1000); // Update every 1 second
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null); // Remove pending callbacks to prevent memory leaks
    }
}
