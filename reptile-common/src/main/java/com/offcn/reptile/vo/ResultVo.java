package com.offcn.reptile.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: Reptile
 * @description:
 * @author: 刘宇航
 * @create: 2020-05-27 15:45
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVo<T> {
    private Integer code;

    private String msg;

    private T data;

    public ResultVo(String msg,Integer code){
        this.msg = msg;
        this.code = code;
    }

    public ResultVo(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }

}
