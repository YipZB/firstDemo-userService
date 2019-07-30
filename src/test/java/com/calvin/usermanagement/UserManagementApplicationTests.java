package com.calvin.usermanagement;

import java.util.Random;
import javax.annotation.Resource;

import com.calvin.usermanagement.repo.user.UserMapper;
import com.calvin.usermanagement.service.UserServiceImpl;
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
        long l = 24 * 3600 + new Random().nextInt(1000);
//        long l1 = 24 * 3600 + new Random().nextInt(1000);
//        long l2 = 24 * 3600 + new Random().nextInt(1000);
//        long l3 = 24 * 3600 + new Random().nextInt(1000);
//        long l4 = 24 * 3600 + new Random().nextInt(1000);
//        long l5 = 24 * 3600 + new Random().nextInt(1000);
        System.out.println(l);
//        System.out.println(l1);
//        System.out.println(l2);
//        System.out.println(l3);
//        System.out.println(l4);
//        System.out.println(l5);
    }

}
