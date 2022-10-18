package com.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName Payment
 * @Description TODO
 * @Author lw
 * @Date 2021/2/28 0028 17:44
 * @Vsersion 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment implements Serializable {
    private Long id;
    private String serial;
    private int status;
    private int version;
}
