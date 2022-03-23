package com.ljz.loverspace.utils.token;

import com.ljz.loverspace.common.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

@Data
@AllArgsConstructor
public class ShiroAuthToken implements AuthenticationToken {
    private String token;


    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return Constant.STRING_EMPTY;
    }
}
