package com.asule.springbootreview.event;

//下雨事件
//下雪事件
public class SnowWeatherEvent implements WeatherEvent{

    @Override
    public String getWeatherName() {
        return "snow";
    }

    @Override
    public void doWeather() {
        System.out.println("snowing");
    }
}
