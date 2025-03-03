package com.yince.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 自定义异常类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeyException extends RuntimeException {

    private Integer code;

    private String msg;


}