package com.easthome.service.imp;

import com.easthome.dao.ShoppingDao;
import com.easthome.dao.imp.ShoppingDaoImp;
import com.easthome.entity.Buycar;
import com.easthome.entity.Goods;
import com.easthome.entity.User;
import com.easthome.service.ShoppingService;

import java.util.List;

public class ShoppingServiceImp implements ShoppingService {
    private ShoppingDao shoppingDao = new ShoppingDaoImp();

    @Override
    public List<User> UserPanduan() {//登录判断
        return shoppingDao.UserPanduan();
    }

    @Override
    public List<Goods> findByGoods(int page) {//返回goods表分页信息
        return shoppingDao.findByGoods(page);

    }

    @Override
    public long goodsByCount() {//获取goods的数量
        return shoppingDao.goodsByCount();
    }

    @Override
    public List<Goods> findAll() {//goods表中所有信息
        return shoppingDao.findAll();
    }

    @Override
    public boolean findByGoodsId(int goodsId, int uid, int text) {//查询goods表中指定id的信息
        /*
        * 对购物车进行商品数量的添加操作
        * */
        boolean flag = false;//布尔标记

        //商品的全部信息
        List<Goods> list = findAll();

        for (Goods goods : list) {
            if (goods.getId() == goodsId){
                //System.out.println("传来的goodsId："+goodsId);
                //查询到指定id，获取信息
                //商品名称
                String gname = goods.getGname();
                //价格
                double price = Double.parseDouble(goods.getPrice());
                //数量
                Integer count = goods.getCount();

                //调用购物车信息的方法，查询该用户购物车中是否存在该商品
                List<Buycar> BuycarList = findByUid(uid);
                //System.out.println("BuycarList = "+BuycarList);
                if (BuycarList.size() == 0){//#1
                    //不存在,添加购物车，减少库存
                    System.out.println("进入#1判断");
                    double totalPrice = price*text;

                    //执行库存减少操作
                    shoppingDao.subGoodsCount(goodsId, text);
                    //buycarInsertCount(String gname, double price, int text, double totalPrice)
                    //添加购物车
                    int i = buycarInsertCount(gname, price, text, totalPrice, uid);

                   // System.out.println("i = "+i);
                    flag = i != 0 ? true : false;

                }else {

                    for (Buycar buycar : BuycarList) {
                        if (buycar.getBname().equals(gname)){//比较姓名 #2
                            /*System.out.println("*******-**");
                            System.out.println("购物车的姓名："+buycar.getBname());
                            System.out.println("列表的商品的姓名："+gname);*/

                            //相等，说明该用户购物车中存在该商品，则对商品数量执行update的count添加操作
                            //count = text where user_id = text;
                            System.out.println("#2执行");
                            //buycarUpdateCount(int uid,int text);

                            //执行库存减少操作
                            shoppingDao.subGoodsCount(goodsId, text);
                            //购物车添加
                            int i = shoppingDao.buycarUpdateCount(uid, text, gname);
                            flag = i != 0 ? true : false;
                            return flag;
                        }

                    }

                    //执行到这里 说明不存在该商品,执行inset添加操作 添加该商品  #3
                    //总价
                    System.out.println("#3执行");
                    double totalPrice = price*text;

                    //执行库存减少操作
                    shoppingDao.subGoodsCount(goodsId, text);
                    //buycarInsertCount(String gname, double price, int text, double totalPrice)

                    //购物车添加
                    int i = buycarInsertCount(gname, price, text, totalPrice, uid);

                    flag = i != 0 ? true : false;
                    //添加成功
                }


            }
        }

        return flag;
    }
    public int buycarInsertCount(String gname, double price, int text, double totalPrice, int uid){
        //购物车不存在该商品的insert操作
        return shoppingDao.buycarInsertCount(gname, price, text, totalPrice, uid);
    }

    @Override
    public List<Buycar> findByUid(int uid) {//在该方法查询指定用户id的购物车表的信息
        return shoppingDao.findByUid(uid);
    }

    //查询指定goodsId的商品数量信息


    @Override
    public List<Goods> findAllByGoodsCount(int goodsId) {
        return shoppingDao.findAllByGoodsCount(goodsId);
    }

    //查询购物车中所有内容
    @Override
    public List<Buycar> findAllBuycar(int uid) {
        return shoppingDao.findAllBuycar(uid);
    }

    @Override
    public boolean buycarGoodsByHandle(String buycarName, int uid) {
        //获取该商品的数量
        int count = shoppingDao.buycarByGoodsCount(buycarName, uid);
        if (count > 1){
            //如果商品数量大于1，执行数量的减一操作
            int i = shoppingDao.buycarSub1(buycarName, uid);
            return i != 0 ? true : false;
        }else {
            //说明商品库存为1.进行删除操作
            int i = shoppingDao.buycarDel(buycarName, uid);
            return i != 0 ? true : false;
        }
    }
}
