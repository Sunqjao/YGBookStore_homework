package com.sun;

import com.sun.dao.BookDao;
import com.sun.dao.CartDao;
import com.sun.dao.UserDao;
import com.sun.entity.Book;
import com.sun.entity.Cart;
import com.sun.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.jws.soap.SOAPBinding;
import java.io.InputStream;

public class Test {
    public static void main(String[] args) {
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        System.out.println(inputStream);
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

//        UserDao userDao = sqlSession.getMapper(UserDao.class);
//        User user = new User();
//
//        user.setUserName("snq");
//        user.setPassword("snq");
//        System.out.println(userDao.findUser(user));
//        user.setId(2);
//        user.setUserName("bb");
//        user.setPassword("bb");
//        user.setAddress("bb");
//        user.setMoney((float) 5.23);
//        user.setPhone("456789");
////        userDao.addUser(user);
//        userDao.update(user);

        BookDao bookDao = sqlSession.getMapper(BookDao.class);
//        System.out.println(bookDao.findBookLike("k1"));
//        System.out.println(bookDao.findOneBook(1));
        System.out.println(bookDao.findBookTag(1));
//        Book book = new Book();
//        book.setBid(10);
//        book.setBookName("1");
//        book.setCost((float) 2);
//        book.setPreCost((float) 3);
//        book.setAuthor("1");
//        bookDao.luRuBook(book);

//        CartDao cartDao =sqlSession.getMapper(CartDao.class);
//        Cart cart = new Cart();
//        cart.setUid(1);
//        cart.setBid(2);
//        cart.setNums(2);
//        //cartDao.addCart(cart);
//        //cartDao.removeCart(cart);
//        //cartDao.updateCart(cart);
//        System.out.println(cartDao.findOneCart(cart));
        sqlSession.commit();
        sqlSession.close();
    }
}
