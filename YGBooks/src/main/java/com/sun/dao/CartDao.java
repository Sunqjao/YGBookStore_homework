/**
 *对购物车映射表的操作我想只有增删改
 * 增就是增加
 * 删就是删
 * 至于改只可能改数目
 * service负责修改数据
 * 如果数目不能传来0，如果是0应该在service中剔除
 * 想了下还是要查，service要先得到数据库中原来的值才正确能修改
 * */
package com.sun.dao;

import com.sun.entity.Cart;

public interface CartDao {
    public void addCart(Cart cart);
    public void removeCart(Cart cart);
    public void updateCart(Cart cart);
    public Cart findOneCart(Cart cart);
    public void removeAll(Integer uid);
}
