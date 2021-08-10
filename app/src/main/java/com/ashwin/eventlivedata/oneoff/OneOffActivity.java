package com.ashwin.eventlivedata.oneoff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.ashwin.eventlivedata.Constant;
import com.ashwin.eventlivedata.R;

public class OneOffActivity extends AppCompatActivity {
    private OneOffViewModel oneOffViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oneoff);

        oneOffViewModel = new ViewModelProvider(this).get(OneOffViewModel.class);

        oneOffViewModel.getLiveEvent().observe(this, n -> {
            Log.d(Constant.TAG, this.getClass().getSimpleName() + ": event: " + n);
        });
    }
}
