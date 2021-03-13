package com.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName CommentResult
 * @Description TODO
 * @Author lw
 * @Date 2021/2/28 0028 19:40
 * @Vsersion 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResult<T> {
    private Integer code;
    private String message;
    private T data;

    public CommentResult(Integer code,String message){
        this(code, message, null);
    }
}
