package com.easthome.entity;

import java.util.Objects;

public class Buycar {
    private Integer bid;
    private String bname;
    private Double bprice;
    private Integer bcount;
    private Double total_price;
    private Integer user_id;

    public Buycar() {
    }

    public Buycar(String bname, Double bprice, Integer bcount, Double total_price, Integer user_id) {
        this.bname = bname;
        this.bprice = bprice;
        this.bcount = bcount;
        this.total_price = total_price;
        this.user_id = user_id;
    }

    public Buycar(String bname, Double bprice, Integer bcount, Double total_price) {
        this.bname = bname;
        this.bprice = bprice;
        this.bcount = bcount;
        this.total_price = total_price;
    }

    public Buycar(Integer bid, String bname, Double bprice, Integer bcount, Double total_price, Integer user_id) {
        this.bid = bid;
        this.bname = bname;
        this.bprice = bprice;
        this.bcount = bcount;
        this.total_price = total_price;
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Buycar{" +
                "bid=" + bid +
                ", bname='" + bname + '\'' +
                ", bprice=" + bprice +
                ", bcount=" + bcount +
                ", total_price=" + total_price +
                ", user_id=" + user_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Buycar buycar = (Buycar) o;
        return Objects.equals(bid, buycar.bid) && Objects.equals(bname, buycar.bname) && Objects.equals(bprice, buycar.bprice) && Objects.equals(bcount, buycar.bcount) && Objects.equals(total_price, buycar.total_price) && Objects.equals(user_id, buycar.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bid, bname, bprice, bcount, total_price, user_id);
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public Double getBprice() {
        return bprice;
    }

    public void setBprice(Double bprice) {
        this.bprice = bprice;
    }

    public Integer getBcount() {
        return bcount;
    }

    public void setBcount(Integer bcount) {
        this.bcount = bcount;
    }

    public Double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
