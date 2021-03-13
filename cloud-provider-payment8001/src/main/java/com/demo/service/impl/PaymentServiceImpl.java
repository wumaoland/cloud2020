package com.demo.service.impl;

import com.demo.dao.PaymentDao;
import com.demo.entity.Payment;
import com.demo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName PaymentServiceimpl
 * @Description TODO
 * @Author lw
 * @Date 2021/2/28 0028 20:11
 * @Vsersion 1.0
 **/
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return this.paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return this.paymentDao.getPaymentById(id);
    }
}

