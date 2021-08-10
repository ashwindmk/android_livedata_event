package com.ashwin.eventlivedata.oneoff;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class LiveEvent<T> extends MutableLiveData<T> {
    private class ObserverWrapper<T> implements Observer<T> {
        private Observer<? super T> observer;
        private AtomicBoolean pending = new AtomicBoolean(false);

        ObserverWrapper(Observer<? super T> observer) {
            this.observer = observer;
        }

        @Override
        public void onChanged(T t) {
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(t);
            }
        }

        void newValue() {
            pending.set(true);
        }
    }

    private Set<ObserverWrapper<T>> observers = new HashSet<>();
//    private AtomicBoolean hasValueWithoutFirstObserver = new AtomicBoolean(false);

    @MainThread
    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        for (ObserverWrapper<T> wrapper : observers) {
            if (wrapper.observer.equals(observer)) {
                return;
            }
        }

        ObserverWrapper<T> wrapper = new ObserverWrapper<T>(observer);
        //if (hasValueWithoutFirstObserver.compareAndSet(true, false)) {
            wrapper.newValue();
        //}
        observers.add(wrapper);
        super.observe(owner, wrapper);
    }

    @MainThread
    @Override
    public void observeForever(@NonNull Observer<? super T> observer) {
        for (ObserverWrapper<T> wrapper : observers) {
            if (wrapper.observer.equals(observer)) {
                return;
            }
        }

        ObserverWrapper<T> wrapper = new ObserverWrapper<T>(observer);
        observers.add(wrapper);
        super.observeForever(wrapper);
    }

    @MainThread
    @Override
    public void removeObserver(@NonNull Observer<? super T> observer) {
        if (observer instanceof ObserverWrapper && observers.remove(observer)) {
            super.removeObserver(observer);
            return;
        }

        Iterator<ObserverWrapper<T>> iterator = observers.iterator();
        while (iterator.hasNext()) {
            ObserverWrapper<T> wrapper = iterator.next();
            if (wrapper.observer.equals(observer)) {
                iterator.remove();
                super.removeObserver(wrapper);
                break;
            }
        }
    }

    @MainThread
    @Override
    public void setValue(T value) {
//        if (observers.isEmpty()) {
//            hasValueWithoutFirstObserver.set(true);
//        }
        for (ObserverWrapper<T> wrapper : observers) {
            wrapper.newValue();
        }
        super.setValue(value);
    }
}
