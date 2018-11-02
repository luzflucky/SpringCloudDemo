package com.maomi.coder;

import com.maomi.coder.service.SuccessService;
import com.maomi.coder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ThreadLocalRandom;


/**
 * Created by lucky on 2018/10/31.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableFeignClients
@RestController
public class RibbonServer {

    public static void main(String[] args) {
        SpringApplication.run(RibbonServer.class);
    }

    @Autowired
    private SuccessService successService;

    @Autowired
    private UserService userService;

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @RequestMapping("/success")
    public String querySuccess(){
        return successService.success();
    }


    @RequestMapping("/addUser")
    public void addMap(@RequestParam("id") Integer id){
        User user = new User();
        user.setId(id);
        user.setName("name" + ThreadLocalRandom.current().nextInt());
//        successService.addUser(user);
        userService.addMap(user);
    }

    @RequestMapping("/getUser")
    public User getMap(@RequestParam("id") Integer id){
//        return successService.getUser(id);
        return userService.getMap(id);
    }
}
