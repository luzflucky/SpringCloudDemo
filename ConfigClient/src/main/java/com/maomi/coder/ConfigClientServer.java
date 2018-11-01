package com.maomi.coder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/11/1.
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class ConfigClientServer {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientServer.class);
    }

    @Value("${name}")
    private String name;

    @Value("${age}")
    private Integer age;

    @Value("${password}")
    private String password;

    @RequestMapping("/getUrl")
    public String getUrl(){
        return name+"/"+age+"/"+password;
    }
}
