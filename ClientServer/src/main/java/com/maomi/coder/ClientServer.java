package com.maomi.coder;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lucky on 2018/10/30.
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class ClientServer {

    public static void main(String[] args) {
        SpringApplication.run(ClientServer.class);
    }

    private static Map<Integer,User> map = new ConcurrentHashMap<>();

    @Value("${num.value}")
    private String value;

    @RequestMapping("/success")
    public String queryDemo(){
        return "success" + value;
    }

    @PostMapping("/addUser")
    public void addMap(@RequestBody User user){
        map.put(user.getId(),user);
    }

    @GetMapping("/getUser")
    public User getMap(@RequestParam("id") Integer id){
        return map.get(id);
    }
}
