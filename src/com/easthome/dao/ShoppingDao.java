package com.easthome.dao;

import com.easthome.entity.Buycar;
import com.easthome.entity.Goods;
import com.easthome.entity.User;

import java.util.List;

public interface ShoppingDao {
    //登录判断
    public List<User> UserPanduan();

    //goods表的信息
    public List<Goods> findByGoods(int page);

    //goods表数量
    public long goodsByCount();

    //goods表中所有商品信息
    public List<Goods> findAll();

    //查询购物车表的信息
    public List<Buycar> findByUid(int uid);

    //购物车中不存在该商品的insert操作
    public int buycarInsertCount(String gname, double price, int text, double totalPrice, int uid);

    //购物车中存在该商品，进行count的修改操作
    public int buycarUpdateCount(int uid,int text, String gname);

    //定义一个对商品列表商品库存减少的方法/接口
    //需要用到商品id和商品添加的数量
    public void subGoodsCount(int goodsId, int text);

    //查询指定id的商品信息，数量信息
    public List<Goods> findAllByGoodsCount(int goodsId);


    //查询购物车所有数据
    public List<Buycar> findAllBuycar(int uid);

    //对购物车执行删除操作
    public int buycarDel(String buycarName, int uid);

    //对购物车中的商品数量进行减一操作
    public int buycarSub1(String buycarName, int uid);

    //获取购物车中指定用户id和商品名称的商品数量
    public int buycarByGoodsCount(String buycarName, int uid);
}
