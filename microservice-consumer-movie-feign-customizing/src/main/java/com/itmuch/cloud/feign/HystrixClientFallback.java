package com.itmuch.cloud.feign;

import com.itmuch.cloud.entity.User;
import org.springframework.stereotype.Component;

/**
 * Created by wangongxin on 17/7/14.
 */
@Component
public class HystrixClientFallback implements UserFeignClient{
    @Override
    public User findById(Long id) {
        User user = new User();
        user.setId(0L);
        return user;
    }
}
