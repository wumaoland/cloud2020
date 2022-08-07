package com.aop.controller;

import com.alibaba.fastjson.JSON;
import com.aop.annotation_method.RedissonApiLimit;
import com.aop.annotation_method.practice.Product;
import com.aop.annotation_method.practice.Product2;
import com.aop.annotation_method.practice.UpdatePriceLogAnnoation;
import com.aop.annotation_method.practice.UpdateProductAnnoation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@Slf4j
public class AopTestConroller {
    @Autowired
    private StringRedisTemplate redisTemplate;

    //@BeforeAopLog
    //@AroundAopLog
    //@AfterAopLog
    //@AfterReturningAopLog
    //@AfterThrowingAopLog
    @RedissonApiLimit(tps = 1, rateTime = 10)
    @GetMapping("/testAop/{code}")
    public String testAop(@PathVariable("code") String code) {
        log.info("业务方法执行");
        return "业务方法执行成功";
    }

    /**
     * 修改价格反射
     *
     * @return
     */
    @UpdatePriceLogAnnoation
    @PutMapping("/updatePrice")
    public Product updatePrice(@RequestBody Product updateProduct) {
        Product resultProduct = null;
        String productStr = redisTemplate.opsForValue().get("product");
        if (!StringUtils.isEmpty(productStr)) {
            resultProduct = JSON.parseObject(productStr, Product.class);
            resultProduct.setPrice(updateProduct.getPrice());
            resultProduct.setName(updateProduct.getName());
        } else {
            resultProduct = updateProduct;
        }
        redisTemplate.opsForValue().set("product", JSON.toJSONString(resultProduct));
        return resultProduct;
    }

    /**
     * 修改商品反射
     *
     * @return
     */
    @UpdateProductAnnoation(name="娃哈哈矿泉水",price = "16")
    @PutMapping("/updatePrice2")
    public Product2 updateProduct(@RequestBody Product2 updateProduct) {
        Product2 resultProduct = null;
        String productStr = redisTemplate.opsForValue().get("product");
        if (!StringUtils.isEmpty(productStr)) {
            resultProduct = JSON.parseObject(productStr, Product2.class);
            resultProduct.setProductName(updateProduct.getProductName());
            resultProduct.setPrice(updateProduct.getPrice());
        } else {
            resultProduct = updateProduct;
        }
        redisTemplate.opsForValue().set("product2", JSON.toJSONString(resultProduct));
        return resultProduct;
    }




    public static void main(String[] args) {
        Set<String> objects = new HashSet<>();
        objects.add("1");
        objects.add("2");
        System.out.println(objects.stream().filter(k -> k.equals(String.valueOf(3))).findFirst().orElse(null));
    }
}
