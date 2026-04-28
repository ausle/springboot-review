package com.asule.springbootreview.event;

//下雨事件
public class RainWeatherEvent implements WeatherEvent{

    @Override
    public String getWeatherName() {
        return "rain";
    }

    @Override
    public void doWeather() {
        System.out.println("raining");
    }
}
