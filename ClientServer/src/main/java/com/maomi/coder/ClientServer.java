package com.maomi.coder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 * Created by lucky on 2018/10/30.
 */
@SpringBootApplication
@EnableEurekaClient
public class ClientServer {

    public static void main(String[] args) {
        SpringApplication.run(ClientServer.class);
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Value("${num.value}")
    private String value;

    @RequestMapping("/success")
    public String queryDemo(){
        return "success" + value;
    }
}
