package com.sun.service;

import com.sun.entity.Book;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface BookService {
    public List<Book> getTagBooks(Integer tid);
    public List<Book> searchBooks(String key);
    public Book getOneBook(Integer bid) throws IOException;
}
