package com.csg.controller;



import com.csg.dto.WeatherInfo;
import com.csg.parse.weather.ParserWeather;
import com.csg.parse.weather.impl.Weather;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
  public List play(Model model, @RequestParam String x, @RequestParam String y){
    
    return  parserWeather.getWeather(x,y);
  }



}
