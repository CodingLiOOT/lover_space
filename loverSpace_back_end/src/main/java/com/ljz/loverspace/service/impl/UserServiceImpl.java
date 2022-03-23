package com.ljz.loverspace.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljz.loverspace.entity.User;
import com.ljz.loverspace.mapper.UserMapper;
import com.ljz.loverspace.service.UserService;
import com.ljz.loverspace.utils.exceptionHandler.exception.DefinitionException;
import com.ljz.loverspace.utils.exceptionHandler.exception.ErrorEnum;
import com.ljz.loverspace.utils.token.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<String> getRoles(String username) {
        return userMapper.getRolesByUsername(new QueryWrapper<User>().eq("username", username));
    }

    @Override
    public String login(User user) {
        User userBean=this.lambdaQuery().eq(User::getUsername,user.getUsername()).one();
        if(userBean==null){
            throw new DefinitionException(ErrorEnum.USER_NOT_EXIST);
        }
        if(!userBean.getPassword().equals(user.getPassword())){
            throw new DefinitionException(ErrorEnum.USER_NOT_LOGIN_OR_PASSWORD_ERROR);
        }
        return JWTUtil.sign(user.getUsername());
    }
}