/**
 * 查询一个用户
 * 除此之外就是修改了，修改时可能会修的地方无非那些
 * 还有增加了,我想在添加前应该要查询吧
 * 对于购物车的修改应该放在其它地方
 *
 * */
package com.sun.dao;

import com.sun.entity.User;
public interface UserDao {
    public User findUser(User user);
    public User findUserNoBooks(User user);
    public void addUser(User user);
    public void update(User user);
}
