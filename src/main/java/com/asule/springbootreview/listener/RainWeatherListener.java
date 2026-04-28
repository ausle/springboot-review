package com.asule.springbootreview.listener;

import com.asule.springbootreview.event.RainWeatherEvent;
import com.asule.springbootreview.event.WeatherEvent;

public class RainWeatherListener implements WeatherListener{

    @Override
    public void doListener(WeatherEvent event) {
        //每个监听器处理特定的事件
        if (event instanceof RainWeatherEvent){
            event.doWeather();
        }
    }
}
