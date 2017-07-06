package com.csg.controller;


import com.csg.busi.service.impl.CourseServiceImpl;
import com.csg.dto.VideoDTO;
import com.csg.crawling.redis.RedisSourceManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Created by Silence on 2016/11/11.
 */
@Controller
@AllArgsConstructor
public class IndexController {

  private final static String[] TAGS = {"LETV","PANDA"};

  private final RedisSourceManager redisSourceManager;

  private CourseServiceImpl courseServiceImpl;

  /**
   * 首页
   */
  @GetMapping("/")
  public String home(Model model){
    List<VideoDTO> carouselPics = redisSourceManager.getVideosByKeyAndTag(redisSourceManager.VIDEO_PREFIX_HOME_CAROUSEL_KEY, TAGS[0]);
    List<VideoDTO> recommends = redisSourceManager.getVideosByKeyAndTag(redisSourceManager.VIDEO_PREFIX_HOME_RECOMMEND_KEY, TAGS[0]);
    List<VideoDTO> tvHots = redisSourceManager.getVideosByKeyAndTag(redisSourceManager.VIDEO_PREFIX_HOME_TV_KEY, TAGS[0]);
    List<VideoDTO> animeHots = redisSourceManager.getVideosByKeyAndTag(redisSourceManager.VIDEO_PREFIX_HOME_CARTOON_KEY, TAGS[0]);
    List<VideoDTO> movies = redisSourceManager.getVideosByKeyAndTag(redisSourceManager.VIDEO_PREFIX_HOME_MOVIE_KEY, TAGS[0]);
    List<VideoDTO> tvTops = redisSourceManager.getVideosByKeyAndTag(redisSourceManager.VIDEO_PREFIX_HOME_TV_HOT_KEY, TAGS[0]);
    List<VideoDTO> lives = redisSourceManager.getVideosByKeyAndTag(redisSourceManager.VIDEO_PREFIx_HOME_LIVE_KEY, TAGS[1]);
    model.addAttribute("carouselPics", carouselPics);
    model.addAttribute("recommends", recommends);
    model.addAttribute("tvHots", tvHots);
    model.addAttribute("animeHots", animeHots);
    model.addAttribute("tvTops", tvTops);
    model.addAttribute("lives", lives);
    model.addAttribute("movies", movies);
    model.addAttribute("navIndex", -1);
    return "home";
  }


  /**
   *  解析
   */
  @GetMapping("video")
  public String video(Model model){
    courseServiceImpl.findCourse();
    model.addAttribute("navIndex", 4);
    return "author";
  }

  /**
   *  解析
   */
  @GetMapping("/author")
  public String author(Model model){
    model.addAttribute("navIndex", 4);
    return "author";
  }


  /**
   *  登录
   */
  @GetMapping("/login")
  public String login(){
    return "login";
  }

  /**
   *  注册
   */
  @GetMapping("/register")
  public String register(){
    return "register";
  }

}
