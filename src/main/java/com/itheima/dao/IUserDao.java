package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//用户持久层接口
public interface IUserDao {
    @Select("select * from user")
    List<User> findAll();
}
