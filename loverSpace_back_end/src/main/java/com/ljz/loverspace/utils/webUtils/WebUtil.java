package com.ljz.loverspace.utils.webUtils;

import com.ljz.loverspace.utils.json.JsonUtil;
import com.ljz.loverspace.utils.resultUtils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.ServletResponse;
import java.io.IOException;

@Slf4j
public class WebUtil {
    public static void sendResponse(ServletResponse response, Result body)throws IOException{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(JsonUtil.obj2String(body));
    }
}
