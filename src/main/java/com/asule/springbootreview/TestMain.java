package com.asule.springbootreview;

import com.asule.springbootreview.event.SnowWeatherEvent;
import com.asule.springbootreview.listener.RainWeatherListener;
import com.asule.springbootreview.listener.SnowWeatherListener;
import com.asule.springbootreview.listener.WeatherEventMulticaster;

public class TestMain {

    public static void main(String[] args) {
        WeatherEventMulticaster eventMulticaster = new WeatherEventMulticaster();
        SnowWeatherListener snowWeatherListener = new SnowWeatherListener();
        RainWeatherListener rainWeatherListener = new RainWeatherListener();

        SnowWeatherEvent snowWeatherEvent = new SnowWeatherEvent();
        eventMulticaster.addListener(snowWeatherListener);
        eventMulticaster.addListener(rainWeatherListener);

        eventMulticaster.sendEvent(snowWeatherEvent);
        eventMulticaster.removeListener(snowWeatherListener);
        eventMulticaster.sendEvent(snowWeatherEvent);
    }
}
