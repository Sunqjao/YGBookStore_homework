package com.sun.service;

import com.sun.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface CartService {
    public boolean addToCart(Integer bid,HttpServletRequest request);
    public boolean removeAll(User user);
    public boolean removeOne(User user,Integer bid);
}
