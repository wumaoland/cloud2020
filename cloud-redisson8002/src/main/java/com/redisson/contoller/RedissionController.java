package com.redisson.contoller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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

    /**
     * 分布式锁的使用例子
     *
     * @return
     */
    @GetMapping("/lock")
    public String test() {
        String lockey = "5554564566";
        RLock lock = redissonClient.getLock(lockey);
        try {
            boolean b = lock.tryLock(2, 3, TimeUnit.SECONDS);
            if (b) {
                log.info("线程:{}，获取到了锁", Thread.currentThread().getName());

                Integer stock = Integer.valueOf(stringRedisTemplate.opsForValue().get("stock"));
                if (stock > 0) {
                    stock -= 1;
                    stringRedisTemplate.opsForValue().set("stock", String.valueOf(stock));
                    log.info("库存扣减成功，剩余库存：" + stock);
                } else {
                    log.info("库存不足");
                }
            }

        } catch (Exception e) {
            //log.info("错误信息：{}",e.toString());
            log.info("线程：{} 获取锁失败", Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
        return "end";
    }

    /**
     * 分布式锁
     * tryLock
     * 正确使用方式,这里的leaseTime要注意时间要比被锁住的业务执行时间长才行，不然提前会释放锁，导致并发执行业务的情况依然存在
     * 原理是当当前线程拿到锁还在执行业务的时候，锁在业务执行完之前提前被释放，其他线程也能在业务没执行完前拿到锁。
     */
    @GetMapping("/wait_lock")
    public  void wait_lock() {
        String lockKey = "123";
        RLock lock = this.redissonClient.getLock(lockKey);
        try {
            boolean b = lock.tryLock();
            if (b) {
                Thread.sleep(1000);
                System.out.println("获取锁成功");
            } else {
                System.out.println("没有获取到锁");
            }
        } catch (Exception e) {
            System.out.println("获取锁出现异常");
        } finally {
            if (lock != null && lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }

        }

    }

    /**
     * 限流
     *
     */
    @GetMapping("")
    public static void testThread() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println("线程：" + Thread.currentThread().getName());
            }).start();
        }
    }


    /**
     * 限流
     * RateLimiter
     */
    @GetMapping("/limit_stream")
    public void limitStream() {
        String phone = "17621861420";
        while (true){
            RRateLimiter stream_lock = this.redissonClient.getRateLimiter(phone);

            //每10秒钟产生一个令牌
            stream_lock.trySetRate(RateType.OVERALL, 1, 10, RateIntervalUnit.SECONDS);

            //拿到一个令牌
            boolean b = stream_lock.tryAcquire(1);
            if (b) {
                System.out.println("----------------------向"+phone+"发送短信----------------");
            }


        }

    }

    @GetMapping("/test2")
    public void test2() {
        Map<String ,String> map = new HashMap();
        map.put("age","23");
        map.put("name", "小李");
        //this.stringRedisTemplate.opsForHash().putAll("a",map);
        this.mapRemove("a","age","name");
    }
    public void mapRemove(String mapName,String ...keys){
        for (String key : keys) {
            this.stringRedisTemplate.opsForHash().delete(mapName,key);
        }

    }

}
