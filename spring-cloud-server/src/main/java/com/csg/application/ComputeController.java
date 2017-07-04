package com.csg.application;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ComputeController {

    private DiscoveryClient client;
    @RequestMapping(value = "/add")
    public String add(@RequestParam MultiValueMap paramMap) {
        ServiceInstance instance = client.getLocalServiceInstance();
        return JSONObject.toJSONString(paramMap.get("data"));
    }
}
