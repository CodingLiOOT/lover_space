package com.ljz.loverspace.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljz.loverspace.entity.Role;
import com.ljz.loverspace.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select(
            "select r.roleName from user u, role r, user_role ur ${ew.customSqlSegment}"
    )
    public List<String> getRolesByUsername(QueryWrapper<User> queryWrapper);
}
