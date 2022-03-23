package com.ljz.loverspace;

import com.ljz.loverspace.entity.User;
import com.ljz.loverspace.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest extends LoverSpaceApplicationTests{
    @Autowired
    private UserService userService;

    @Test
    public void test(){
     userService.save(new User("ljz123hhh","123456","qqqqq"));
    }
}
