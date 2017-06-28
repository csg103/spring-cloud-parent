package com.csg.controller;



import com.csg.parse.weather.ParserWeather;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Silence on 2016/12/6.
 */
@RestController
@AllArgsConstructor
public class WeatherController {

  private final ParserWeather parserWeather;

  /**
     * 解析视频
     */
  @GetMapping("/api/getWeather")
  public List play( @RequestParam String x, @RequestParam String y){

    return  parserWeather.getWeather(x,y);
  }



}
