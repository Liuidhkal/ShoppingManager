package com.easthome.service;

import com.easthome.entity.Buycar;
import com.easthome.entity.Goods;
import com.easthome.entity.User;

import java.util.List;

public interface ShoppingService {
    //登录判断
    public List<User> UserPanduan();

    //商品goods表
    public List<Goods> findByGoods(int page);
    //goods的数量
    public long goodsByCount();

    //goods表中所有商品信息
    public List<Goods> findAll();

    //查询商品表中指定id的信息
    public boolean findByGoodsId(int goodsId, int uid, int text);

    //查询购物车表的信息
    public List<Buycar> findByUid(int uid);

    //购物车中不存在该商品的insert操作
    public int buycarInsertCount(String gname, double price, int text, double totalPrice, int uid);

    //查询指定id的商品信息，数量信息
    public List<Goods> findAllByGoodsCount(int goodsId);

    //查询购物车所有数据
    public List<Buycar> findAllBuycar(int uid);

    //执行购物车商品的删除操作，点击一次数量-1，如果商品数量为1，点击进行删除
    //1.先获取该商品的数量，2.数量大于1进行update的-1，3.数量=1进行delete
    public boolean buycarGoodsByHandle(String buycarName, int uid);
}
