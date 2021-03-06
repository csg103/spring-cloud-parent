package com.csg.application.control;

import com.alibaba.fastjson.JSONObject;
import com.csg.application.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CourseController {

    private CourseService courseService;
    private DiscoveryClient client;
    @RequestMapping(value = "/add")
    public String findCourse(@RequestParam MultiValueMap paramMap) {
       List corseList = courseService.findCourse();
        ServiceInstance instance = client.getLocalServiceInstance();
        return JSONObject.toJSONString(corseList);
    }
}
