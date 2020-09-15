package com.calvin.usermanagement;

import java.util.*;
import javax.annotation.Resource;

import com.calvin.usermanagement.repo.user.User;
import com.calvin.usermanagement.repo.user.UserMapper;
import com.calvin.usermanagement.service.UserServiceImpl;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserManagementApplicationTests {
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Resource
    private UserMapper userMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void contextLoads() {
//        Map<String, User> map = new HashMap<>();
//        map.put("aaa", null);
//        System.out.println(map.getOrDefault("aaa", new User()));

//            eventMsgMap.computeIfAbsent(deviceId, k -> new HashMap<>(10))
//                        .computeIfAbsent(alarmType, k -> new ArrayList<>())
//                        .add(eventMsg);

    }
}
