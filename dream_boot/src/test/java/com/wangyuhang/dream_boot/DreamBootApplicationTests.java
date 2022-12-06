package com.wangyuhang.dream_boot;

import com.wangyuhang.dream_boot.entity.Sensitive;
import com.wangyuhang.dream_boot.mapper.SensitiveMapper;
import com.wangyuhang.dream_boot.service.CollectService;
import com.wangyuhang.dream_boot.service.SensitiveService;
import com.wangyuhang.dream_boot.service.UserService;
import org.apache.shiro.authc.UnknownAccountException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;

import java.net.UnknownServiceException;

@SpringBootTest
class DreamBootApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private CollectService collectService;

    @Autowired
    private SensitiveMapper sensitiveMapper;

    @Autowired
    private SensitiveService sensitiveService;

    @Test
    void contextLoads() {
        System.out.println(userService.findByUserName("12"));
        if (ObjectUtils.isEmpty(userService.findByUserName("12"))) {
            System.out.println("true");
        }
    }

    @Test
    public void testForValue1(){
        String key = "wyh";
        String value = "king";
        redisTemplate.opsForValue().set(key, value);
        System.out.println(redisTemplate.opsForValue().get("wyh"));
        System.out.println(redisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void a1(){
        String contentId = String.valueOf(222);
        if (redisTemplate.opsForValue().get(contentId) == null){
            redisTemplate.opsForValue().set(contentId,222);
            redisTemplate.opsForValue().increment(contentId);
            System.out.println("缓存不存在"+redisTemplate.opsForValue().get(contentId));
        }else{
            redisTemplate.opsForValue().increment(contentId);
            System.out.println("缓存存在"+redisTemplate.opsForValue().get(contentId));
        }

    }

    @Test
    public void a2(){
        Sensitive sensitive = new Sensitive();
        sensitive.setName("王唯楚");
        sensitiveService.add(sensitive);



    }



}
