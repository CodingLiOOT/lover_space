package com.ljz.loverspace.utils.resultUtils;

import com.ljz.loverspace.utils.exceptionHandler.exception.DefinitionException;
import com.ljz.loverspace.utils.exceptionHandler.exception.ErrorEnum;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class Result {
    private Integer code;
    private String msg;
    private Object data;

    public static Result success(Object data) {
        return new Result()
                .code(0)
                .msg("成功")
                .data(data);
    }
    public static Result success(String msg,Object data) {
        return new Result()
                .code(0)
                .msg(msg)
                .data(data);
    }
    public static Result success(String msg) {
        return new Result()
                .code(0)
                .msg(msg);
    }
    public static Result fail(String msg) {
        return new Result()
                .code(1)
                .msg(msg);
    }
    public static Result fail(Integer code,String msg) {
        return new Result()
                .code(code)
                .msg(msg);
    }
    public static Result fail(Integer code,String msg,Object data) {
        return new Result()
                .code(code)
                .msg(msg)
                .data(data);
    }

    public static Result fail(ErrorEnum errorEnum) {
        return new Result()
                .code(errorEnum.getCode())
                .msg(errorEnum.getMessage());
    }
    public static Result fail(DefinitionException e) {
        return new Result()
                .code(e.getCode())
                .msg(e.getMessage());
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
