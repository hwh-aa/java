package com.etc.feigninters;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserFeignClientFallback implements UserFeignClient{

    @Override
    public Map<String, Object> getUserById(Integer uid) {
        Map<String,Object> map = new HashMap<>();
        map.put("uid","-1");
        map.put("uname","-1");
        map.put("password","-1");
        return map;
    }
}
