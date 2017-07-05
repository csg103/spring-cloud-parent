package com.csg.controller;

import com.csg.busi.service.impl.ComputeServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CourseController {
    private ComputeServiceImpl computeServiceImpl;

//    @Autowired
//  RestTemplate restTemplate;
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
       return computeServiceImpl.addService();
        //return restTemplate.getForEntity("http://COMPUTE-SERVICEA/add?a=10&b=20", String.class).getBody();
    }
}