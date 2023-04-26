package com.easthome.controller;

import com.easthome.entity.Goods;
import com.easthome.service.ShoppingService;
import com.easthome.service.imp.ShoppingServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
* 在这里执行商品列表中的添加操作
* */
@WebServlet("/shopping")
public class ShoppingController extends HttpServlet {
    private ShoppingService shoppingService = new ShoppingServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取商品goodsId和购买的数量text
        int goodsId = Integer.parseInt(req.getParameter("goodsId"));
        int text = Integer.parseInt(req.getParameter("text"));

        //1.接下来查询商品表中该id的商品，获取该商品的名称，价格，数量
        // 2.接下来查询购物车表 该用户 是否存在该商品 存在则修改数量，不存在则添加

        //获取当前登录用户的uid
        int uid =(int) req.getSession().getAttribute("uid");
        //将goodsId 用户id传入方法中判断查找
        boolean flag = shoppingService.findByGoodsId(goodsId,uid,text);

        //查询指定uid的商品goods表信息 , 商品数量的信息
        List<Goods> goodsCountList = shoppingService.findAllByGoodsCount(goodsId);
        int count =(int) goodsCountList.get(0).getCount();
        //System.out.println("count = " + count);


        //System.out.println("执行");
        //System.out.println(flag);

        resp.setCharacterEncoding("utf-8");

        if (flag){
            //添加成功
            resp.getWriter().print(count);
            //req.getRequestDispatcher("goods?start=1").forward(req,resp);
        }else {
            //添加失败
            resp.getWriter().write("-1");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
