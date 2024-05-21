package com.example.zturf.animations;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import com.example.zturf.R;

public class Animation extends AppCompatActivity {
Button animations , graphics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        animations = findViewById(R.id.button);
        graphics = findViewById(R.id.button2);

        animations.setOnClickListener(v->{
            startActivity(new Intent(this,Animationmy.class));
        });

        graphics.setOnClickListener(v->{
            startActivity(new Intent(this,Graphics.class));
        });
    }
}