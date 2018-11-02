package com.maomi.coder.service;

import com.maomi.coder.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



/**
 * Created by lucky on 2018/11/1.
 */
@Service
public class SuccessService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "errorFallBack")
    public String success(){
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return restTemplate.getForEntity("http://CLIENT-SERVER/success",String.class).getBody();
    }

    public String errorFallBack(){
        return "error";
    }

//    @CacheRemove(commandKey = "getUser", cacheKeyMethod = "getCacheKey")
    @HystrixCommand
    public void addUser(User user){
        restTemplate.postForEntity("http://CLIENT-SERVER/addUser",user,User.class);
    }

//    @CacheResult(cacheKeyMethod = "getCacheKey")
    @HystrixCommand
    public User getUser(Integer id){
       return restTemplate.getForEntity("http://CLIENT-SERVER/getUser?id={1}",User.class,id).getBody();
    }

    public String getCacheKey(Integer id){
        return String.valueOf(id);
    }
}
