package com.example.zturf.multimedia;

import androidx.appcompat.app.AppCompatActivity;


import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.zturf.R;

public class VideoPlayer extends AppCompatActivity {
   VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        videoView = findViewById(R.id.videoView);
        setupVideoPlayback();
    }

    private void setupVideoPlayback() {
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video;
        Uri videoUri = Uri.parse(videoPath);
        videoView.setVideoURI(videoUri);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.start();
    }

}