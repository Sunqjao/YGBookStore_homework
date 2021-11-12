package com.sun.service.impl;

import com.sun.Test;
import com.sun.dao.BookDao;
import com.sun.dao.UserDao;
import com.sun.entity.Book;
import com.sun.service.BookService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
import java.net.URLDecoder;
import java.util.List;

public class BookServiceImpl implements BookService {
    InputStream inputStream;
    SqlSessionFactoryBuilder sqlSessionFactoryBuilder;
    SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession;

    private BookDao getBookDao(){
        inputStream = Test.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        System.out.println(inputStream);
        sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        System.out.println("4-410");
        sqlSession = sqlSessionFactory.openSession();
        System.out.println("4-411");
        BookDao bookDao = sqlSession.getMapper(BookDao.class);
        System.out.println("4-412");
        return bookDao;
    }

    @Override
    public List<Book> getTagBooks(Integer tid) {
        return getBookDao().findBookTag(tid);
    }

    @Override
    public List<Book> searchBooks(String key) {
        System.out.println("service:"+key);
        return getBookDao().findBookLike(key);
    }

    @Override
    public Book getOneBook(Integer bid) throws IOException {
        Book book = getBookDao().findOneBook(bid);
        return book;
    }
}
