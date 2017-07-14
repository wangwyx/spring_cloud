package com.itmuch.cloud.feign;

/**
 * Created by wangongxin on 17/7/14.
 */
public class FeignClient2FallBack implements FeignClient2{
    @Override
    public String findServiceInfoFromEurekaByServiceName(String serviceName) {
        return "asdfasdfasd";
    }
}
