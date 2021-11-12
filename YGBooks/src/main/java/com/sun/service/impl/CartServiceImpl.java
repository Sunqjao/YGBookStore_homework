package com.sun.service.impl;

import com.sun.Test;
import com.sun.dao.BookDao;
import com.sun.dao.CartDao;
import com.sun.dao.UserDao;
import com.sun.entity.Book;
import com.sun.entity.Cart;
import com.sun.entity.User;
import com.sun.service.CartService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.List;

public class CartServiceImpl implements CartService {
    InputStream inputStream;
    SqlSessionFactoryBuilder sqlSessionFactoryBuilder;
    SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession;

    private CartDao getCartDao(){
        inputStream = Test.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        System.out.println(inputStream);
        sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        System.out.println("4-410");
        sqlSession = sqlSessionFactory.openSession();
        System.out.println("4-411");
        CartDao cartDao = sqlSession.getMapper(CartDao.class);
        System.out.println("4-412");
        return cartDao;
    }
    @Override
    public boolean addToCart(Integer bid, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Integer uid = user.getId();
        Cart cart = new Cart();
        cart.setBid(bid);
        cart.setUid(uid);
        CartDao cartDao = getCartDao();

        System.out.println("service======");
        if(cartDao.findOneCart(cart)==null){
            cart.setNums(1);
            cartDao.addCart(cart);
            System.out.println("service======:"+cartDao);
        }else {
            int num = 0;
            List<Book> books = user.getBooks();
            for (Book book:books) {
                if(book.getBid()==bid){
                    num = book.getNums();
                }
            }

            cart.setNums(num+1);
            cartDao.updateCart(cart);
            System.out.println("service======:"+cartDao);
        }
        System.out.println("service:"+sqlSession);
        sqlSession.commit();
        sqlSession.close();

        return false;
    }

    @Override
    public boolean removeAll(User user) {
        CartDao cartDao = getCartDao();
        cartDao.removeAll(user.getId());
        sqlSession.commit();
        sqlSession.close();
        return false;
    }

    @Override
    public boolean removeOne(User user, Integer bid) {
        CartDao cartDao = getCartDao();
        cartDao.removeCart(new Cart(user.getId(),bid,0));
        sqlSession.commit();
        sqlSession.close();
        return false;
    }
}
