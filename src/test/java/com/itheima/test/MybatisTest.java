package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;
    private IAccountDao accountDao;

    @Before
    public void init() throws Exception {
        //1 读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2 创建SqlSessionFactory工厂，创建者模式
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3 使用工厂生产SqlSession对象  工厂模式
        sqlSession = factory.openSession();
        //4 使用SqlSession创建Dao接口的代理对象    代理模式
        userDao = sqlSession.getMapper(IUserDao.class);
        accountDao = sqlSession.getMapper(IAccountDao.class);
    }

    @After
    public void destroy() throws Exception {
        //插入时必须加上提交事务，否则不生效
        sqlSession.commit();
        //释放资源
        sqlSession.close();
        in.close();
    }

    //---------------------------select-----------------------------------------------------
    //all
    @Test
    public void testFindAll() {
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId(1);
        user.setAddress("12121221");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setUsername("王121五");

        userDao.updateUser(user);
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setAddress("456456");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setUsername("王asd五");

        userDao.insertUser(user);
//        System.out.println("新增记录之后的主键ID" + user.getId());
    }

    @Test
    public void deleteUser() {
        userDao.deleteUser(1);
    }

    @Test
    public void testFindUserById() {

        List<User> users = userDao.findUserById(2);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindAllAccount() {
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }
}
