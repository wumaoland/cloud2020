package com.redisson.contoller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.redisson.api.RedissonClient;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


/**
 * @ClassName RedissionController
 * @Description TODO
 * @Author lw
 * @Date 2021/3/13 0013 15:22
 * @Vsersion 1.0
 **/
@RestController
@Slf4j
public class RedissionController {
    @Resource
    private RedissonClient redissonClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/test")
    public  String test(){
        String lockey="5554564566";
        RLock lock = redissonClient.getLock(lockey);
        try {
            boolean b = lock.tryLock(2, 3, TimeUnit.SECONDS);
            if (b) {
                log.info("线程:{}，获取到了锁",Thread.currentThread().getName());

                Integer stock = Integer.valueOf(stringRedisTemplate.opsForValue().get("stock"));
                if (stock >0) {
                    stock-=1;
                    stringRedisTemplate.opsForValue().set("stock",String.valueOf(stock));
                    log.info("库存扣减成功，剩余库存："+stock);
                }else{
                    log.info("库存不足");
                }
            }

       }catch (Exception e){
            //log.info("错误信息：{}",e.toString());
            log.info("线程：{} 获取锁失败",Thread.currentThread().getName());
        }finally {
            lock.unlock();
        }
        return "end";
    }

}
