package com.demo.controller;

import com.demo.entity.CommentResult;
import com.demo.entity.Payment;
import com.demo.service.PaymentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName PaymentController
 * @Description TODO
 * @Author lw
 * @Date 2021/2/28 0028 21:25
 * @Vsersion 1.0
 **/
@RestController
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommentResult create(@RequestBody Payment payment) {
        int i = this.paymentService.create(payment);
        System.out.println("aaaaaa");
        if (i > 0) {
            return new CommentResult(200, "插入数据成功", i);
        } else {
            return new CommentResult(444, "插入数据库失败", null);
        }
    }

    @GetMapping("/payment/{id}")
    public CommentResult getPaymentById(@PathVariable("id") Long id) {
        Payment paymentById = this.paymentService.getPaymentById(id);
        if (paymentById != null) {
            return new CommentResult(200, "获取数据成功", paymentById);
        } else {
            return new CommentResult(444, "获取数据失败", paymentById);
        }

    }

    /**
     * 乐观锁测试
     * @param id
     */
    @PutMapping("/payment/{id}")
    public void updatePayment(@PathVariable("id") Long id) {
        //先查询
        Payment paymentById = this.paymentService.getPaymentById(id);

        //后修改
        Payment payment = Payment.builder().id(id).version(paymentById.getVersion()).build();
        this.paymentService.updatePayment(payment);
    }
}
