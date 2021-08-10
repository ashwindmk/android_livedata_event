package com.ashwin.eventlivedata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.ashwin.eventlivedata.oneoff.OneOffActivity;
import com.ashwin.eventlivedata.unicast.EventActivity;
import com.ashwin.eventlivedata.unicast.EventViewModel;

public class MainActivity extends AppCompatActivity {
    private EventViewModel eventViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button producerButton = findViewById(R.id.unicast_button);
        producerButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, EventActivity.class));
        });

        Button oneOffButton = findViewById(R.id.oneoff_button);
        oneOffButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, OneOffActivity.class));
        });
    }
}
