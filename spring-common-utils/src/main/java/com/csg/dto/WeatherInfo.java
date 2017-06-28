package com.csg.dto;


import lombok.Data;

/**
 * 天气对象
 *
 * @author csg
 * @create 2017-06-23:56
 */
@Data
public class WeatherInfo {
    private  String time;
    private String weatherAM;
    private String weatherPM;
    private String topTemperature;
    private  String lowTemperature;
}
