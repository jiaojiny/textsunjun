package com.xibuguigu.yygh.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class YyghException extends RuntimeException{
    private Integer code;
    private String msg;


    public YyghException(Integer code, String msg,Exception e) {
        super(e);
        this.code = code;
        this.msg = msg;
    }
    public YyghException(String msg, Integer code){
        this.msg  = msg;
        this.code = code;
    }
}
