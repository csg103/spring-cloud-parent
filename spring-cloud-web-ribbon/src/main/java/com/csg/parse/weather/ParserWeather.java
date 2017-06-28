package com.csg.parse.weather;

import java.util.List;

/**
 * 转换天气接口
 *
 * @author csg
 * @create 2017-06-0:06
 */

public interface ParserWeather {

    List getWeather(String lat, String lon);
}
