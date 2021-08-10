package com.ashwin.eventlivedata.unicast;

import java.util.concurrent.atomic.AtomicBoolean;

public class Event<T> {
    private final T value;
    private final AtomicBoolean hasBeenHandled;

    public Event(T value) {
        this.value = value;
        hasBeenHandled = new AtomicBoolean(false);
    }

    public T getIfNotHandled() {
        if (hasBeenHandled.compareAndSet(false, true)) {
            return value;
        }
        return null;
    }

    public T get() {
        return value;
    }
}
