package com.csg.crawling.parse;


import com.alibaba.fastjson.JSONObject;
import com.csg.dto.Video;
import com.csg.dto.WeatherInfo;
import com.csg.crawling.parse.video.Letv;
import com.csg.tools.UrlUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Silence on 2017/4/14.
 */
@Service
@Log4j2
public class VideoParserManager implements ParserManager {

    private final StringRedisTemplate redisTemplate;

    private Map<String, Parser> parserMap;

    /**
     * 初始化所有解析器
     */
    public VideoParserManager(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        parserMap = new HashMap<String, Parser>();
        parserMap.put("le.com", new Letv());
//        parserMap.put("imooc.com", new Imooc());
//        parserMap.put("panda.tv", new Panda());
//        parserMap.put("mp.weixin.qq.com", new Weixin());
//        parserMap.put("blog.didispace.com", new Didispace());
    }

    public Parser getParser(String key) {
        return parserMap.get(key);
    }

    /**
     * 解析视频
     */
    public Video parseVideo(String url) {
        String cacheValue = redisTemplate.opsForValue().get(url);
        cacheValue="";
        Video video;
        if (StringUtils.isEmpty(cacheValue)) {
            log.info("Parse：" + url);
            String key = UrlUtils.getTopDomain(url);
            Parser videoParser = this.getParser(key);
            video = (Video) videoParser.parse(url);
            this.cacheVideoInfo(url, video);
        } else {
            log.info("Get cache info：" + url);
            video = JSONObject.parseObject(cacheValue, Video.class);
        }
        log.debug("VIDEO: " + JSONObject.toJSONString(video));
        return video;
    }

    @Override
    public WeatherInfo getWeather(String lat, String lon) {
        return null;
    }

//    /**
//     * 解析文章
//     */
//    public Article parseArticle(String url) {
//        String key = UrlUtils.getDomain(url);
//        Parser articleParser = this.getParser(key);
//        return (Article) articleParser.parse(url);
//    }

    /**
     * 缓存视频信息
     */
    private void cacheVideoInfo(String url, Video video) {
        if (StringUtils.isNotEmpty(video.getPlayUrl())) {
            log.info("Cache video：" + url);
            String value = JSONObject.toJSONString(video);
            redisTemplate.opsForValue().set(url, value);
            redisTemplate.expire(url, 3, TimeUnit.HOURS);
        }
    }

}


