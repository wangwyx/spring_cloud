package com.itmuch.cloud.feign;

import com.itmuch.cloud.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wangongxin on 17/7/14.
 */

@FeignClient(name="microservice-provider-user",fallback = HystrixClientFallback.class)
public interface UserFeignClientWithHystrix {
    @RequestMapping(value = "/simple2/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id);// 两个坑：1. @GetMapping不支持   2. @PathVariable得设置value
}

