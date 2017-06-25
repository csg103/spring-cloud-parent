package com.csg.application;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

import static org.bouncycastle.asn1.ua.DSTU4145NamedCurves.params;

@Service
public class ComputeService {
    @Autowired
    RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod = "addServiceFallback")
    public String addService() {
//                 HttpHeaders headers = new HttpHeaders();
//                MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
//             headers.setContentType(type);
//                headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        UserInfoPOJO UserInfoPOJO =new UserInfoPOJO();
        UserInfoPOJO.setUsername("张萨姆");
        UserInfoPOJO.setPwd("11111");
        UserInfoPOJO.setText1("wocacac");
//              String jsonObj = JSONObject.toJSONString(UserInfoPOJO);
//               HttpEntity<String> formEntity = new HttpEntity<String>(jsonObj, headers);
//                Map map =new HashMap<Integer,UserInfoPOJO>();
//        map.put(1,UserInfoPOJO);
//        String Mes = JSONObject.toJSONString(map);
            //  String url =;

        MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<String, String>();
        paramMap.add("data", JSONObject.toJSONString(UserInfoPOJO));

  String aa= restTemplate.postForObject("http://COMPUTE-SERVICEA/add", paramMap, String.class);
        JSONArray  mapreturn = JSONObject.parseArray(aa);
        String strreturn = (String)mapreturn.get(0);
        UserInfoPOJO aaa =  JSON.parseObject(strreturn,UserInfoPOJO.getClass());
return aa;

     //  return restTemplate.getForEntity("http://COMPUTE-SERVICEA/add?a=10&b=20&c="+Mes, String.class).getBody();
    }

    public String addServiceFallback() {
        return "error";
    }
}