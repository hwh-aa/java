package com.etc.feigninters;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

//定义一个feign接口，通过FeignClient（“服务名”），来制定消费哪个服务
@FeignClient(name = "springclouduser",fallback = UserFeignClientFallback.class)//要消费的微服务的名字
public interface UserFeignClient {

    //value请求地址就是羞涩人用户微服务中该控制器的接口地址
    @RequestMapping("/user/get/{uid}")
    public Map<String,Object> getUserById(@PathVariable("uid") Integer uid);
}
