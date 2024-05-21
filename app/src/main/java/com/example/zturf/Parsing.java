package com.example.zturf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.zturf.json.Json;
import com.example.zturf.xml.Xml;

public class Parsing extends AppCompatActivity {

    Button xml , json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parsing2);
        xml = findViewById(R.id.button);
        json = findViewById(R.id.button2);

        xml.setOnClickListener(v->{
            startActivity(new Intent(Parsing.this, Xml.class));
        });

        json.setOnClickListener(v->{
            startActivity(new Intent(Parsing.this, Json.class));
        });
    }
}