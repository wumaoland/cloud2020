package com.easyexcel.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class Person {
    private String name;
    private Boolean sex;
    private int age;
    private BigDecimal saveMoney;
}
