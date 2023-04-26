package com.easthome.controller;

import com.easthome.service.ShoppingService;
import com.easthome.service.imp.ShoppingServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
* 在这里执行购物车中商品的删除操作
* 根据商品名称和登录的用户id删除商品，每点击一次，删除数量1
* */
@WebServlet("/buycarDelCon")
public class BuycarDelController extends HttpServlet {
    private ShoppingService shoppingService = new ShoppingServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车中要删除的商品名称
        String buycarName = req.getParameter("buycarName");
        //获取当前账号的user_id
        int uid =(int) req.getSession().getAttribute("uid");

        //调用购物车商品删除处理的方法
        boolean flag = shoppingService.buycarGoodsByHandle(buycarName, uid);

        //设置响应字符集
        resp.setCharacterEncoding("utf-8");
        if (flag){
            //删除成功
            resp.getWriter().write("1");
        }else {
            //删除失败
            resp.getWriter().write("-1");

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
