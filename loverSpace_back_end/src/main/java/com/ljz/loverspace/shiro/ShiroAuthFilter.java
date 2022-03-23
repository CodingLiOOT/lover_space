package com.ljz.loverspace.shiro;

import com.ljz.loverspace.utils.exceptionHandler.exception.DefinitionException;
import com.ljz.loverspace.utils.exceptionHandler.exception.ErrorEnum;
import com.ljz.loverspace.utils.resultUtils.Result;
import com.ljz.loverspace.utils.token.ShiroAuthToken;
import com.ljz.loverspace.utils.webUtils.WebUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ShiroAuthFilter extends BasicHttpAuthenticationFilter {


   @Override
   protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
      String authHeader = getAuthzHeader(request);
      return new ShiroAuthToken(authHeader);
   }

   @Override
   //存疑，如果不能成功catch异常，就写WebUtil进行发送response
   protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
      AuthenticationToken token = this.createToken(request, response);
      if(token == null) {
//         WebUtil.sendResponse(response, Result.fail(ErrorEnum.UNAUTHORIZED));
         throw new DefinitionException(ErrorEnum.UNAUTHORIZED);
      } else {
         try {
            Subject subject = this.getSubject(request, response);
            subject.login(token);
            return this.onLoginSuccess(token, subject, request, response);
         } catch (AuthenticationException e) {
            WebUtil.sendResponse(response, Result.fail(ErrorEnum.UNAUTHORIZED));
            return false;
         }
      }
   }

   @Override
   protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
      boolean r;
      String authorization = getAuthzHeader(request);
      if(StringUtils.isBlank(authorization)){
         WebUtil.sendResponse(response, Result.fail(ErrorEnum.UNAUTHORIZED));
         r = false;
      }else{
         r=executeLogin(request, response);
      }
      return r;
   }

}
