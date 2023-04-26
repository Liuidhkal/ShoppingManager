package com.easthome.dao.imp;

import com.easthome.dao.ShoppingDao;
import com.easthome.entity.Buycar;
import com.easthome.entity.Goods;
import com.easthome.entity.User;
import com.easthome.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class ShoppingDaoImp implements ShoppingDao {
    private QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
    @Override
    public List<User> UserPanduan() {//在该方法中判断用户名密码
        //查询user表中的uername和password
        String sql = "select * from user";
        List<User> list = null;
        try {
            list = qr.query(sql, new BeanListHandler<User>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //System.out.println(list);

        return list;
    }

    @Override
    public List<Goods> findByGoods(int page) {//在该方法中返回goods商品表的信息
        int temp = (page-1) > 0 ? (page-1)*3 : 0;

        String sql = "select * from goods limit ?,3";
        List<Goods> list = null;
        try {
            list = qr.query(sql, new BeanListHandler<Goods>(Goods.class), temp);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;

    }

    @Override
    public long goodsByCount() {//商品数量

        String sql = "select count(*) from goods";
        long count = 0;
        try {
            count =(long) qr.query(sql, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    //查询goods表中所有信息

    @Override
    public List<Goods> findAll() {

        String sql = "select * from goods";
        List<Goods> list = null;
        try {
            list = qr.query(sql, new BeanListHandler<Goods>(Goods.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Buycar> findByUid(int uid) {//查询指定uid用户的购物车信息
        String sql = "select * from buycar where user_id = ?";

        List<Buycar> list = null;
        try {
            list = qr.query(sql, new BeanListHandler<Buycar>(Buycar.class), uid);
            System.out.println("findByUid执行！ = "+list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //购物车中不存在该商品的insert操作
    public int buycarInsertCount(String gname, double price, int text, double totalPrice, int uid){
        String sql = "insert into buycar values(?,?,?,?,?,?)";
        Object[] param = {null, gname, price, text, totalPrice, uid};
        try {
            int i = qr.update(sql, param);
            System.out.println("购物车不存在该商品进行insert操作，i = "+i);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //购物车中存在该商品，对count执行修改操作  ：获取原来的数量，进行相加
    public int buycarUpdateCount(int uid,int text, String gname){
        String sql = "update buycar set bcount = (bcount + ?) , total_price = (total_price+bprice) " +
                "where user_id = ? and bname = ?";
        Object[] param = {text, uid, gname};
        try {
            int i = qr.update(sql, param);
            System.out.println("购物车中存在该商品，对count进行update修改操作，i = "+i);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //商品库存减少的方法
    @Override
    public void subGoodsCount(int goodsId, int text) {
        String sql = "update goods set count = (count-?) where id = ?";
        Object[] param = {text, goodsId};
        try {
            qr.update(sql, param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //查询指定商品id的信息 数量信息

    @Override
    public List<Goods> findAllByGoodsCount(int goodsId) {
        String sql = "select * from goods where id = ?";
        try {
            List<Goods> goodsCountList = qr.query(sql, new BeanListHandler<Goods>(Goods.class), goodsId);
            return goodsCountList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //查询购物车中所有内容
    @Override
    public List<Buycar> findAllBuycar(int uid) {
        String sql = "select * from buycar where user_id = ?";
        try {
            List<Buycar> buycarList = qr.query(sql, new BeanListHandler<Buycar>(Buycar.class), uid);
            return buycarList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //对购物车中的商品进行删除操作
    @Override
    public int buycarDel(String buycarName, int uid) {
        String sql = "delete from buycar where bname = ? and user_id = ?";
        Object[] param = {buycarName, uid};
        try {
            int i = qr.update(sql, param);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //对购物车中指定商品的数量进行减一操作
    @Override
    public int buycarSub1(String buycarName, int uid) {
        String sql = "update buycar set bcount = (bcount-1) where bname = ? and user_id = ?";
        Object[] param = {buycarName, uid};
        try {
            int i = qr.update(sql, param);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //获取购物车中指定用户id和商品名称的商品数量
    @Override
    public int buycarByGoodsCount(String buycarName, int uid) {
        String sql = "select * from buycar where bname = ? and user_id = ?";
        Object[] param = {buycarName, uid};
        try {
            List<Buycar> list = qr.query(sql, new BeanListHandler<Buycar>(Buycar.class), param);
            //该商品的数量
            int buycarGoodsCount = list.get(0).getBcount();
            return buycarGoodsCount;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
