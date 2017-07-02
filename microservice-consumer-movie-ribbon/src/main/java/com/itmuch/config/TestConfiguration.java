package com.itmuch.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.NativeWebRequest;

/**Configuration不能在  @ComponentScan (or @SpringBootApplication) 所扫描的包下
 * Created by wyx on 2017/6/27.
 */
@Configuration
public class TestConfiguration {
    @Autowired
    IClientConfig clientConfig;
    @Bean
    public IRule ribbonRule(IClientConfig clientConfig) {

        return new RandomRule();
    }
}
