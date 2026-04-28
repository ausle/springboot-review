package com.asule.springbootreview.listener;

import com.asule.springbootreview.event.WeatherEvent;

//事件监听接口
public interface WeatherListener {
    //接收一个事件
    void doListener(WeatherEvent event);
}
