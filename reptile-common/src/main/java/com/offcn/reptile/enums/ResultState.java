package com.offcn.reptile.enums;

import lombok.Getter;

/**
 * @program: Reptile
 * @description:
 * @author: 刘宇航
 * @create: 2020-05-27 16:38
 **/
public enum  ResultState {
    SUCCESS(0,"响应成功");

    @Getter
    private int code;

    @Getter
    private String msg;

    ResultState(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
