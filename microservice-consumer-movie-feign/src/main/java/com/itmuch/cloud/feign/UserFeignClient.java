package com.itmuch.cloud.feign;

import com.itmuch.cloud.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wangongxin on 17/7/10.
 */
@FeignClient("microservice-provider-user")
public interface UserFeignClient {
    @RequestMapping(value = "/simple/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id);// 两个坑：1. @GetMapping不支持   2. @PathVariable得设置value

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User postUser(@RequestBody User user);

    @RequestMapping(value = "/get-user", method = RequestMethod.GET)// 该请求不会成功，只要参数是复杂对象，即使指定了是GET方法，feign依然会以POST方法进行发送请求
    public User getUser(User user);

}
