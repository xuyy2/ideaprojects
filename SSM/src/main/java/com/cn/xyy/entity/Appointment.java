package com.cn.xyy.entity;

import java.util.Date;

public class Appointment {
    private long bookId;// 图书ID

    private long studentId;// 学号

    private Date appointTime;// 预约时间

    // 多对一的复合属性
    private Book book;// 图书实体

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getAppointTime() {

        return appointTime;
    }

    public void setAppointTime(Date appointTime) {
        this.appointTime = appointTime;
    }

    public long getStudentId() {

        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getBookId() {

        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    // 省略构造方法，getter和setter方法，toString方法
}
