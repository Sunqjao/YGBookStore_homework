package com.sun.entity;

import java.io.Serializable;
import java.util.List;

public class Book implements Serializable {
    private Integer bid;
    private String bookName;
    private Float cost;
    private Float preCost;
    private String author;
    private String detail;
    private String picUrl;
    private Integer nums;
    private List<String> tags;

    public Book() {
    }


    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Float getPreCost() {
        return preCost;
    }

    public void setPreCost(Float preCost) {
        this.preCost = preCost;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bid=" + bid +
                ", bookName='" + bookName + '\'' +
                ", cost=" + cost +
                ", preCost=" + preCost +
                ", author='" + author + '\'' +
                ", detail='" + detail + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", nums=" + nums +
                ", tags=" + tags +
                '}';
    }
}
