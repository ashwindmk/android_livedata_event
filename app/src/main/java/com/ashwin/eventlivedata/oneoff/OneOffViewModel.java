package com.ashwin.eventlivedata.oneoff;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class OneOffViewModel extends ViewModel {
    private LiveEvent<Integer> liveEvent = new LiveEvent<>();

    public LiveData<Integer> getLiveEvent() {
        return liveEvent;
    }

    public void updateCount() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Integer x = liveEvent.getValue();
                if (x == null) x = 0;
                liveEvent.setValue(x + 1);
            }
        }, 5000);
    }
}
