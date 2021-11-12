package com.sun.controller;

import com.sun.Test;
import com.sun.dao.BookDao;
import com.sun.entity.Book;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;

@Controller
public class bookLuRuController {
    @RequestMapping("luRu")
    public String luRu(Book book, @RequestParam(value = "de",defaultValue="")String de, HttpServletRequest request) throws IOException {
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        System.out.println(inputStream);
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        BookDao bookDao = sqlSession.getMapper(BookDao.class);
        bookDao.luRuBook(book);

        sqlSession.commit();
        sqlSession.close();

        String path = request.getSession().getServletContext().getRealPath("/bookDe");
        File realPath = new File(path);
        if(!realPath.exists()){
            realPath.mkdir();
        }
        System.out.println("文件路径:"+realPath);

        Writer writer = new FileWriter(new File(realPath,"bid"+book.getBid()+".txt"));
        writer.write(de);
        writer.close();
        return "bookLuRu";
    }
}
