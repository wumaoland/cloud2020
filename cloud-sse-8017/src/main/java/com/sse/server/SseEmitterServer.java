package com.sse.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

@Slf4j
public class SseEmitterServer {
    /**
     * 当前连接数，AtomicInteger，一个提供原子操作的Integer的类。在Java语言中，++i和i++操作并不是线程安全的，在使用的时候，不可避免的会用到synchronized关键字。而AtomicInteger则通过一种线程安全的加减操作接口。
     * <p>
     * AtomicInteger的API：
     * 获取当前的值
     * public final int get()
     * <p>
     * 取当前的值，并设置新的值
     * public final int getAndSet(int newValue)
     * <p>
     * 获取当前的值，并自增
     * public final int getAndIncrement()
     * <p>
     * 获取当前的值，并自减
     * public final int getAndDecrement()
     * <p>
     * 获取当前的值，并加上预期的值
     * public final int getAndAdd(int delta)
     */
    private static AtomicInteger count = new AtomicInteger(0);

    /**
     * 使用map对象，便于根据userId来获取对应的SseEmitter，或者放redis里面
     */
    private static Map<String, SseEmitter> sseEmitterMap = new ConcurrentHashMap<>();

    /**
     * 创建连接
     *
     * @param userId
     * @return 设置连接结束的回调方法completionCallBack
     * 设置连接超时的回调方法timeoutCallBack
     * 设置连接异常的回调方法errorCallBack
     * 创建推送信息的方法SseEmitter.send()
     */
    public static SseEmitter connect(String userId) {
        try {
            log.info("用户:{}连接进来了",userId);
            // 设置超时时间,0表示不过期,默认30秒,超过时间未完成会抛出异常：AsyncRequestTimeoutException
            SseEmitter sseEmitter = new SseEmitter(0L);
            // 注册回调
            sseEmitter.onCompletion(completionCallBack(userId));
            sseEmitter.onError(errorCallBack(userId));
            sseEmitter.onTimeout(timeoutCallBack(userId));
            sseEmitterMap.put(userId, sseEmitter);
            return sseEmitter;
        } catch (Exception e) {
            log.info("创建新的sse连接异常，当前用户：{}", userId);
        }
        return null;
    }

    /**
     * 推送消息
     *
     * @param userId
     * @param message
     */
    public static void sendMessage(String userId, String message) {
        if (sseEmitterMap.containsKey(userId)) {
            try {
                sseEmitterMap.get(userId).send(message);
            } catch (IOException e) {
                log.error("向{}用户推送消息[{}]失败,异常{}", userId, message, e);
                removeUser(userId);
            }
        }
    }

    /**
     * 群发消息
     */
    public static void batchSendMessage(String wsInfo, List<String> ids) {
        ids.forEach(userId -> sendMessage(wsInfo, userId));
    }

    /**
     * 获取当前连接用户id
     *
     * @return
     */
    public static List<String> getIds() {
        return new ArrayList<>(sseEmitterMap.keySet());
    }

    /**
     * 获取当前连接数量
     *
     * @return
     */
    public static int getUserCount() {
        return count.intValue();
    }

    private static Runnable completionCallBack(String userId) {
        return () -> {
            log.info("结束连接:{}", userId);
            removeUser(userId);
        };
    }

    private static Consumer<Throwable> errorCallBack(String userId) {
        return Throwable -> {
            log.info("连接异常:{}", userId);
            removeUser(userId);
        };
    }

    private static Runnable timeoutCallBack(String userId) {
        return () -> {
            log.info("连接超时:{}", userId);
            removeUser(userId);
        };
    }

    public static void removeUser(String userId) {
        sseEmitterMap.remove(userId);
        //数量减一
        count.getAndDecrement();
        log.info("移除用户:{}", userId);
    }


}
