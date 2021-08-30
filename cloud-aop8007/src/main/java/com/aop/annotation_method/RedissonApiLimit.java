package com.aop.annotation_method;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedissonApiLimit {
    /**
     * 令牌数，默认0不限流
     *
     * @return
     */
    int tps() default 0;

    /**
     * 限流时间
     * @return
     */
    int rateTime() default 1;

    /**
     * 限流唯一key
     */
    String key() default "";
}
