package com.sun.service;

import com.sun.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    public boolean register(User user);
    public boolean login(User user, HttpServletRequest request);
    public User getInformation(HttpServletRequest request);
    public boolean updateInfo(User user,HttpServletRequest request);
    public boolean recharge(Float money,HttpServletRequest request);
    public boolean payFor(Float money,HttpServletRequest request);
}
