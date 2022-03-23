package com.ljz.loverspace.common;

public class Constant {
    public static final Integer CODE_SUCCESS = 1;
    public static final Integer CODE_FAIL = 0;

    // token失效，前端接收到这个值后，就给出提示信息并跳转到登录页
    public static final Integer CODE_TOKEN_ERROR = 12345;

    public static final String STRING_EMPTY = "";

    // 过期时间
    //public static final long EXPIRE_TIME = 1000*60;
    public static final long EXPIRE_TIME = 1000 * 60 * 60 * 24;

    protected static final String AUTHORIZATION_HEADER = "Authorization";
}
