package com.ashwin.eventlivedata.unicast;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ashwin.eventlivedata.Constant;

public class EventViewModel extends ViewModel {
    private MutableLiveData<Event<Integer>> countLiveData = new MutableLiveData<>(new Event<>(0));

    public LiveData<Event<Integer>> getCountLiveData() {
        return countLiveData;
    }

    public void updateCount() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Integer x = countLiveData.getValue().get();
                countLiveData.postValue(new Event<>(x + 1));
            }
        }, 5000);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(Constant.TAG, this.getClass().getSimpleName() + ": onCleared");
    }
}
