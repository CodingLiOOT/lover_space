package com.ljz.loverspace.controller;

import com.ljz.loverspace.entity.User;
import com.ljz.loverspace.service.UserService;
import com.ljz.loverspace.utils.exceptionHandler.exception.DefinitionException;
import com.ljz.loverspace.utils.exceptionHandler.exception.ErrorEnum;
import com.ljz.loverspace.utils.resultUtils.ResponseResultBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@ResponseResultBody
@CrossOrigin
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public Map<String,String> login(@RequestBody User user) {
        if(StringUtils.isAnyBlank(user.getUsername(), user.getPassword())) {
            throw new DefinitionException(ErrorEnum.USER_NAME_OR_PASSWORD_EMPTY);
        }
        Map<String,String> map = new HashMap<>();
        map.put("token", userService.login(user));
        return map;
    }

    @PostMapping(value = "/register")
    public String register() {
        return "register";
    }
}
