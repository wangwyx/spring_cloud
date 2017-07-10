package com.itmuch.config;

import com.itmuch.cloud.ExcludeFromComponentScan;
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
/*当The FooConfiguration has to be @Configuration but take care that it is not in a @ComponentScan for the main application context, otherwise it will be shared by all the @RibbonClients. If you use @ComponentScan (or @SpringBootApplication) you need to take steps to avoid it being included (for instance put it in a separate, non-overlapping package, or specify the packages to scan explicitly in the @ComponentScan).
添加此注解
@ExcludeFromComponentScan
*/
public class TestConfiguration {
    @Autowired
    IClientConfig clientConfig;
    @Bean
    public IRule ribbonRule(IClientConfig clientConfig) {

        return new RandomRule();
    }
}
