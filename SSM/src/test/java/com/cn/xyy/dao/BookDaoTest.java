package com.cn.xyy.dao;

import com.cn.xyy.BaseTest;
import com.cn.xyy.dao.BookDao;
import com.cn.xyy.entity.Book;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookDaoTest extends BaseTest {
    @Autowired
    private BookDao bookDao;

    @Test
    public void testQueryById() throws Exception{
        long bookId = 1000;
        Book book = bookDao.queryById(bookId);
        System.out.println(book.getName());
    }

    @Test
    public void testQueryAll() throws Exception {
        List<Book> books = bookDao.queryAll(0, 4);
        for (Book book : books) {
            System.out.println(book.getName());
        }
    }

    @Test
    public void testReduceNumber() throws Exception {
        long bookId = 1000;
        int update = bookDao.reduceNumber(bookId);
        System.out.println("update=" + update);
    }
}