package com.easthome.entity;

import java.util.Objects;

public class Goods {
    private Integer id;
    private String gname;
    private String price;
    private Integer count;

    public Goods() {
    }

    public Goods(Integer id, String gname, String price) {
        this.id = id;
        this.gname = gname;
        this.price = price;
    }

    public Goods(Integer id, String gname, String price, Integer count) {
        this.id = id;
        this.gname = gname;
        this.price = price;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", gname='" + gname + '\'' +
                ", price='" + price + '\'' +
                ", count=" + count +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return Objects.equals(id, goods.id) && Objects.equals(gname, goods.gname) && Objects.equals(price, goods.price) && Objects.equals(count, goods.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gname, price, count);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
