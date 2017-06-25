package com.csg.application;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@AllArgsConstructor
public class ConsumerController {
    private ComputeService computeService;

//    @Autowired
//  RestTemplate restTemplate;
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
       return computeService.addService();
        //return restTemplate.getForEntity("http://COMPUTE-SERVICEA/add?a=10&b=20", String.class).getBody();
    }
}