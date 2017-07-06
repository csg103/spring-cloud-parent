package com.csg.crawling.parse.weather.impl;

import com.csg.dto.WeatherInfo;
import com.csg.crawling.parse.weather.ParserWeather;
import com.csg.tools.JsoupUtils;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析天气
 *
 * @author csg
 * @create 2017-06-0:04
 */
@Service
@Log4j2
@AllArgsConstructor
public class WeatherImpl implements ParserWeather {
    private static final String WEATHER = "http://m.weather.com.cn/mtown/index";


    @Override
    public List getWeather(String lat, String lon) {
        String url = WEATHER + "?lon=" + lat.substring(0, 9) + "&lat=" + lon.substring(0, 9);
        Document document = JsoupUtils.getDocWithPhone(url);

        Elements div = document.select("div.days7 ul li");
        List list = new ArrayList();
        for (Element element : div) {

            WeatherInfo weatherInfo = new WeatherInfo();
            Elements li = element.select("li");
            Elements time = li.select("b");
            weatherInfo.setTime(time.text());
            Elements img1 = li.select("i img[src]");

            weatherInfo.setWeatherAM(img1.get(0).attr("src"));
            if(img1.size()>1)
            weatherInfo.setWeatherPM(img1.get(1).attr("src"));

            Elements temperature = li.select("span");
            weatherInfo.setTemperature(temperature.text());

            list.add(weatherInfo);
        }
        return list;
    }
}
