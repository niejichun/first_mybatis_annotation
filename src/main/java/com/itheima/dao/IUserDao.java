package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

//用户持久层接口
public interface IUserDao {
    @Select("select * from user")
    @Results(id = "userMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "address", property = "address"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "birthday", property = "birthday"),
            @Result(property = "accounts", column = "id", many = @Many(select = "com.itheima.dao.IAccountDao.findAccountByUid", fetchType = FetchType.LAZY))
    })
    List<User> findAll();

    @Update("update user set username=#{username},birthday=#{birthday},sex=#{sex},address=#{address} where id=#{id}")
    void updateUser(User user);

    @Insert("insert user (username,birthday,sex,address) values (#{username},#{birthday},#{sex},#{address})")
    void insertUser(User user);

    @Delete("delete from user where id=#{userId}")
    void deleteUser(Integer userId);

    @Select("select * from user where id=#{id}")
    List<User> findUserById(Integer id);
}
