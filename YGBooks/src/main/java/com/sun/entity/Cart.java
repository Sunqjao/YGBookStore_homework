package com.sun.entity;

public class Cart {
    private Integer uid;
    private Integer bid;
    private Integer nums;

    public Cart() {
    }

    public Cart(Integer uid, Integer bid, Integer nums) {
        this.uid = uid;
        this.bid = bid;
        this.nums = nums;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    @Override
    public String toString() {
        return "cart{" +
                "uid=" + uid +
                ", bid=" + bid +
                ", nums=" + nums +
                '}';
    }
}
