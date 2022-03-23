package com.ljz.loverspace.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@TableName("users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(type = IdType.ASSIGN_UUID)
    private String  id;

    private String nickname;

    private String password;

    private String username;

    @TableField(exist = false)
    private Set<String> roles;

    public User(String username, String password,String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }
}
