package com.maomi.coder.service;

import com.maomi.coder.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by lucky on 2018/11/1.
 */
@FeignClient("CLIENT-SERVER")
public interface UserService {

    @PostMapping("/addUser")
    void addMap(@RequestBody User user);

    @GetMapping("/getUser")
    User getMap(@RequestParam("id") Integer id);
}
