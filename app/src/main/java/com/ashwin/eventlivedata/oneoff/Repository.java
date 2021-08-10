package com.ashwin.eventlivedata.oneoff;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class Repository {
    private MutableLiveData<Integer> countLiveData = new MutableLiveData<>(0);

    public LiveData<Integer> getCountLiveData() {
        return countLiveData;
    }

    public void updateCount() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Integer x = countLiveData.getValue();
                countLiveData.postValue(x + 1);
            }
        }, 5000);
    }
}
