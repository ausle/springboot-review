package com.asule.springbootreview.event;

//天气事件的接口
public interface WeatherEvent {

    //天气名字
    String getWeatherName();

    //该天气下会发生什么
    void doWeather();
}
