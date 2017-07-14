package com.itmuch.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.itmuch.cloud.entity.User;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Arrays;
import java.util.List;

@RestController
@EnableCircuitBreaker
@SessionScope
public class MovieController {
  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private LoadBalancerClient loadBalancerClient;
  @GetMapping("/movie/{id}")

  @HystrixCommand(fallbackMethod = "findByIdFallback",commandProperties = @HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE"))
  public User findById(@PathVariable Long id) {

    //vip 虚拟IP microservice-provider-user
    return this.restTemplate.getForObject("http://microservice-provider-user/simple/" + id, User.class);
  }

  public User findByIdFallback(@PathVariable Long id) {
    User user = new User();
    user.setId(0L);
    return user;
  }
  @GetMapping("/test")
  public String test() {
    ServiceInstance serviceInstance = this.loadBalancerClient.choose("microservice-provider-user");
    System.out.println("111"+serviceInstance.getHost()+serviceInstance.getPort()+": "+serviceInstance.getServiceId());

    ServiceInstance serviceInstance2 = this.loadBalancerClient.choose("microservice-provider-user2");
    System.out.println("222"+serviceInstance2.getHost()+serviceInstance2.getPort()+": "+serviceInstance2.getServiceId());


    return "1";
  }
  @GetMapping("list-all")
  public List<User> listall(){
    // wrong
    //    List<User> list = this.restTemplate.getForObject("http://microservice-provider-user/list-all", List.class);
    //    for (User user : list) {
    //      System.out.println(user.getId());
    //    }

    // right
    User[] users = this.restTemplate.getForObject("http://microservice-provider-user/list-all", User[].class);
    List<User> list2 = Arrays.asList(users);
    for (User user : list2) {
      System.out.println(user.getId());
    }

    return list2;
  }


}
