package com.ashwin.eventlivedata.unicast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.ashwin.eventlivedata.Constant;
import com.ashwin.eventlivedata.R;

public class EventActivity extends AppCompatActivity {
    private EventViewModel eventViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);

        eventViewModel.getCountLiveData().observe(this, new UnicastObserver<Integer>() {
            @Override
            void onUpdated(Integer data) {
                Log.d(Constant.TAG, EventActivity.this.getClass().getSimpleName() + ": count: " + data);
            }
        });
    }
}