package com.sun.service.impl;

import com.sun.Test;
import com.sun.controller.Controller;
import com.sun.dao.UserDao;
import com.sun.entity.User;
import com.sun.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.InputStream;

public class UserServiceImpl implements UserService {
    InputStream inputStream;
    SqlSessionFactoryBuilder sqlSessionFactoryBuilder;
    SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession;

    private UserDao getUserDao(){
        inputStream = Test.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        System.out.println(inputStream);
        sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        System.out.println("4-410");
        sqlSession = sqlSessionFactory.openSession();
        System.out.println("4-411");
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        System.out.println("4-412");
        return userDao;
    }

    @Override
    public boolean register(User user) {
        UserDao userDao = getUserDao();
        User u = userDao.findUserNoBooks(user);
        System.out.println("service:u"+u);
        if(u==null){
            user.setMoney((float)0.0);
            userDao.addUser(user);
            sqlSession.commit();
            sqlSession.close();
            return true;
        }else{
            sqlSession.commit();
            sqlSession.close();
            return false;
        }

    }

    @Override
    public boolean login(User user, HttpServletRequest request) {
        UserDao userDao = getUserDao();
        HttpSession session = request.getSession();
        User u1 = userDao.findUserNoBooks(user);
        if(u1!=null){
            User u = userDao.findUser(user);
            if(u==null){
                session.setAttribute("user",u1);
            }else{
                session.setAttribute("user",u);
            }

            System.out.println("登录成功"+u1);
            return true;
        }else{
            System.out.println("信息service登录失败");
            return false;
        }





    }

    @Override
    public User getInformation(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user==null||user.getId()==null){
            User us = new User();
            us.setUserName("未登录");
            us.setPhone("");
            us.setMoney((float)0.00);
            us.setAddress("");
            return us;
        }else{
            if(user.getAddress()==null){
                user.setAddress("");
            }
            return user;
        }
    }
    /**
     * 信息修改时
     * 应该有一个校验机制
     * 可以在前端传入一个信息（id）
     * 与session进行校验查看是否是同一个用户
     * 但是我就不考率这些了
     * */
    @Override
    public boolean updateInfo(User user,HttpServletRequest request) {
        System.out.println("1---service:"+user);
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        user.setId(u.getId());
        user.setMoney(u.getMoney());
        user.setBooks(u.getBooks());
        System.out.println("2---service:"+user);
        System.out.println("3.0----"+u);
        if(user.getUserName()==null||user.getUserName().equals("")){
            user.setUserName(u.getUserName());
        }
        System.out.println("4-55");
        if(user.getPassword()==null||user.getPassword().equals("")){
            user.setPassword(u.getPassword());
        }
        System.out.println("4-59");
        if(user.getPhone()==null||user.getPhone().equals("")){
            user.setPhone(u.getPhone());
        }
        System.out.println("4-77");
        if(user.getAddress()==null||user.getAddress().equals("")){
            user.setAddress(u.getAddress());
        }
        System.out.println("4-95");
        UserDao userDao = getUserDao();
        System.out.println("4-555service:"+user);
        userDao.update(user);
        System.out.println(sqlSession);
        sqlSession.commit();
        sqlSession.close();
        session.setAttribute("user",user);
        return true;
    }

    @Override
    public boolean recharge(Float money, HttpServletRequest request) {
        System.out.println(money);
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        if(money<0){
            money=(float)0;
        }
        money += u.getMoney();
        u.setMoney(money);
        UserDao userDao = getUserDao();
        System.out.println("=========");
        userDao.update(u);
        System.out.println(sqlSession);
        sqlSession.commit();
        sqlSession.close();
        return true;
    }

    @Override
    public boolean payFor(Float money, HttpServletRequest request) {
        System.out.println(money);
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");

        money = u.getMoney()-money;
        u.setMoney(money);
        UserDao userDao = getUserDao();
        System.out.println("=========");
        userDao.update(u);
        System.out.println(sqlSession);
        sqlSession.commit();
        sqlSession.close();
        return true;
    }


}
