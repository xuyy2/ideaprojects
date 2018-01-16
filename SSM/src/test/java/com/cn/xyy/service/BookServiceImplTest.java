package com.cn.xyy.service;

import com.cn.xyy.BaseTest;
import com.cn.xyy.service.impl.BookServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cn.xyy.dto.AppointExecution;
import com.cn.xyy.service.BookService;

public class BookServiceImplTest extends BaseTest {

    @Autowired
    private BookService bookService;

    @Test
    public void testAppoint() throws Exception {
        long bookId = 1001;
        long studentId = 12345678910L;
        AppointExecution execution = bookService.appoint(bookId,studentId);
        System.out.println(execution);
    }

}