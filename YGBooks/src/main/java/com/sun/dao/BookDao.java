/**
 * 模糊查询
 * 标签查询 需要一个标签表和映射表 进行三表联查
 * 单本查询
 **/
package com.sun.dao;

import com.sun.entity.Book;
import java.util.List;

public interface BookDao {
    public List<Book> findBookLike(String key);
    public List<Book> findBookTag(Integer tagID);
    public Book findOneBook(Integer bid);
    public void luRuBook(Book book);
}
