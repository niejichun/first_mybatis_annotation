package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

//用户持久层接口
public interface IUserDao {
    @Select("select * from user")
    List<User> findAll();

    @Update("update user set username=#{username},birthday=#{birthday},sex=#{sex},address=#{address} where id=#{id}")
    void updateUser(User user);

    @Insert("insert user (username,birthday,sex,address) values (#{username},#{birthday},#{sex},#{address})")
    void insertUser(User user);

    @Delete("delete from user where id=#{userId}")
    void deleteUser(Integer userId);

}
