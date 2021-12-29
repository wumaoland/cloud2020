package com.redisson;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Test
    public void testMap(){
        Map<Object, Object> map = this.stringRedisTemplate.opsForHash().entries("abc");
        for (Object o : map.keySet()) {
            String key = String.valueOf(o);
            String value =String.valueOf(map.get(o));
            System.out.println(value);
        }
    }

    @Test
    public void testGetTime(){
        Map<Object, Object> timeConfigMap = stringRedisTemplate.opsForHash().entries("abc");
        String dateNow = DateUtil.format(new Date(), "yyyy-MM-dd");
        for (Object o : timeConfigMap.keySet()) {
            String startDateTime= new StringBuilder(dateNow).append(" ").append(o.toString()).toString();
            String endDateTime= new StringBuilder(dateNow).append(" ").append(timeConfigMap.get(o).toString()).toString();
            System.out.println(startDateTime+"="+endDateTime);
        }
    }

    @Test
    public void testDelMap(){
        this.stringRedisTemplate.delete("abc");
    }

    @Test
    public void returnMap(){
        Map<Object, Object> abc = this.stringRedisTemplate.opsForHash().entries("abc");
        System.out.println(JSONObject.toJSONString(abc));
    }
}
