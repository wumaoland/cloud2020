package com.aop.annotation_method.practice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product2 {
    private String productName;
    private BigDecimal price;

    @Override
    public String toString() {
        return "Product2{" +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }
}
