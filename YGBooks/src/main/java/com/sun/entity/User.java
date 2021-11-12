package com.sun.entity;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private Integer uid;
    private String userName;
    private String password;
    private String address;
    private Float money;
    private String phone;
    private List<Book> books;

    public User() {
    }
    public User(User user) {
        this.uid = user.uid;
        this.userName = user.userName;
        this.password = user.password;
        this.address = user.address;
        this.money = user.money;
        this.phone = user.phone;
        this.books = user.books;
    }
    public User(Integer uid, String userName, String password, String address, Float money, String phone, List<Book> books) {
        this.uid = uid;
        this.userName = userName;
        this.password = password;
        this.address = address;
        this.money = money;
        this.phone = phone;
        this.books = books;
    }

    public Integer getId() {
        return uid;
    }

    public void setId(Integer id) {
        this.uid = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", money=" + money +
                ", phone='" + phone + '\'' +
                ", books=" + books +
                '}';
    }
}
