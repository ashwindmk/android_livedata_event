package com.ashwin.eventlivedata.unicast;

import android.util.Log;

import androidx.lifecycle.Observer;

import com.ashwin.eventlivedata.Constant;

public abstract class UnicastObserver<T> implements Observer<Event<T>> {
    @Override
    public void onChanged(Event<T> event) {
        Log.d(Constant.TAG, "UnicastObserver.onChanged( " + event.get() + " )");
        if (event.getIfNotHandled() != null) {
            this.onUpdated(event.get());
        }
    }

    abstract void onUpdated(T data);
}
