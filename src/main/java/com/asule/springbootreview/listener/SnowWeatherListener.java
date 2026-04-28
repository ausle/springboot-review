package com.asule.springbootreview.listener;

import com.asule.springbootreview.event.SnowWeatherEvent;
import com.asule.springbootreview.event.WeatherEvent;

public class SnowWeatherListener implements WeatherListener{

    @Override
    public void doListener(WeatherEvent event) {
        if (event instanceof SnowWeatherEvent){
            event.doWeather();
        }
    }
}
