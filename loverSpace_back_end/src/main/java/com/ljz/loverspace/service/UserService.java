package com.ljz.loverspace.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljz.loverspace.entity.User;

import java.util.List;


public interface UserService extends IService<User> {
    List<String> getRoles(String username);
    String login(User user);
}
