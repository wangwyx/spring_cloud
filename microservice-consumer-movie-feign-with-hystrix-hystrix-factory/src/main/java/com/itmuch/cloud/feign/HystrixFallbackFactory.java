package com.itmuch.cloud.feign;

import com.itmuch.cloud.entity.User;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by wangongxin on 17/7/16.
 */
@Component
public class HystrixFallbackFactory implements FallbackFactory<UserFeignClient> {
    private static final Logger LOGGER = LoggerFactory.getLogger(HystrixFallbackFactory.class);
    @Override
    public UserFeignClient create(Throwable cause) {
        LOGGER.info("fallback; reason was: " + cause.getMessage());
        return new UserFeignClientWithFactory() {
            @Override
            public User findById(Long id) {
                User user = new User();
                user.setId(-1L);
                return user;
            }
        };
    }
}
