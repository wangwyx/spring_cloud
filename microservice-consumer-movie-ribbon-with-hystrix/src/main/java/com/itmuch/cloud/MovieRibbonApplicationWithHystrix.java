package com.itmuch.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient

public class MovieRibbonApplicationWithHystrix {

  @Bean
  @LoadBalanced/*整合ribbon*/
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(MovieRibbonApplicationWithHystrix.class, args);
    Thread.sleep(3000);

  }
}
