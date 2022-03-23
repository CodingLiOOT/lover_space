package com.ljz.loverspace.utils.exceptionHandler.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorEnum {
    // 系统级别的错误
    SUCCESS(200, "成功"),
    FAIL(400, "失败"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源不存在"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    BAD_GATEWAY(502, "网关错误"),
    SERVICE_UNAVAILABLE(503, "服务不可用"),
    GATEWAY_TIMEOUT(504, "网关超时"),
    // 业务级别的错误
    // 数据库错误
    DATABASE_ERROR(1000, "数据库错误"),
    // 用户错误
    USER_NOT_EXIST(1001, "用户不存在"),
    USER_PASSWORD_ERROR(1002, "密码错误"),
    USER_NOT_LOGIN(1003, "用户未登录"),
    USER_NOT_ADMIN(1004, "用户不是管理员"),
    USER_NOT_AUTHORIZED(1005, "用户没有权限"),
    USER_NOT_LOGIN_OR_PASSWORD_ERROR(1006, "用户名或密码错误"),
    USER_LOGIN_FAIL(1007, "用户登录失败"),
    USER_NAME_OR_PASSWORD_EMPTY(1008, "用户名或密码不能为空");

    private final int code;
    private final String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
