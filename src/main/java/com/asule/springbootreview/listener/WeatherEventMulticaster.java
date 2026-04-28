package com.asule.springbootreview.listener;

public class WeatherEventMulticaster extends EventMulticaster{
    @Override
    protected void doEnd() {
        System.out.println("WeatherEventMulticaster doEnd......");
    }

    @Override
    protected void doStart() {
        System.out.println("WeatherEventMulticaster doStart......");
    }
}
