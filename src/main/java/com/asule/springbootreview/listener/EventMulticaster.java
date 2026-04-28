package com.asule.springbootreview.listener;

import com.asule.springbootreview.event.WeatherEvent;

import java.util.ArrayList;
import java.util.List;

//事件广播器
public abstract class EventMulticaster {

    //里面有个集合来维护所有的监听器。用事件广播器发送一个事件后，会遍历所有监听器的监听方法。
    //若有监听器可以处理该事件，就调用事件的处理方法。
    private List<WeatherListener> listeners = new ArrayList<>();

    public void addListener(WeatherListener listener) {
        listeners.add(listener);
    }

    public void removeListener(WeatherListener listener) {
        listeners.remove(listener);
    }

    public void sendEvent(WeatherEvent event) {
        doStart();
        for (WeatherListener listener : listeners) {
            listener.doListener(event);
        }
        doEnd();
    }

    //发送事件前后的抽象
    protected abstract void doEnd();

    protected abstract void doStart();
}
