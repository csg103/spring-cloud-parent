package com.csg.busi.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.csg.application.UserInfoPOJO;
import com.csg.application.dto.CourseDto;
import com.csg.busi.service.CourseService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    RestTemplate restTemplate;

    public List findCourse() {
//                 HttpHeaders headers = new HttpHeaders();
//                MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
//             headers.setContentType(type);
//                headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        UserInfoPOJO UserInfoPOJO = new UserInfoPOJO();
        UserInfoPOJO.setUsername("张萨姆");
        UserInfoPOJO.setPwd("11111");
        UserInfoPOJO.setText1("wocacac");

        MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<String, String>();
        paramMap.add("data", JSONObject.toJSONString(UserInfoPOJO));

        String courseListJson = restTemplate.postForObject("http://COMPUTE-SERVICEA/add", paramMap, String.class);
//        JSONArray  mapreturn = JSONObject.parseArray(aa);
//        String strreturn = (String)mapreturn.get(0);
        List courseList = JSON.parseArray(courseListJson, CourseDto.class);
        return courseList;

        //  return restTemplate.getForEntity("http://COMPUTE-SERVICEA/add?a=10&b=20&c="+Mes, String.class).getBody();
    }


}