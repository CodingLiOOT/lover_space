package com.ljz.loverspace.shiro;

import com.ljz.loverspace.utils.exceptionHandler.exception.ErrorEnum;
import com.ljz.loverspace.utils.resultUtils.Result;
import com.ljz.loverspace.utils.webUtils.WebUtil;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class ShiroPermissionsAuthorizationFilter extends PermissionsAuthorizationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        WebUtil.sendResponse(servletResponse, Result.fail(ErrorEnum.FORBIDDEN));
        return false;
    }
}
