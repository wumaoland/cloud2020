package com.easyexcel.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PersonExcel {
    @ExcelProperty("名字")
    private String name;
    @ExcelProperty("性别")
    private String sex;
    @ExcelProperty("年龄")
    private int age;
    @ExcelProperty("存款(元)")
    private BigDecimal saveMoney;

    public PersonExcel(String name, Boolean sex, int age, BigDecimal saveMoney) {
        this.name = name;
        this.sex = sex ? "男":"女";
        this.age = age;
        this.saveMoney = saveMoney;
    }
}
