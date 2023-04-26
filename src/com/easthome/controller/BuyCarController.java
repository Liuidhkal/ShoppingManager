package com.easthome.controller;

import com.easthome.entity.Buycar;
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
* 在这里实现购物车内容的显示
* */
@WebServlet("/buycar")
public class BuyCarController extends HttpServlet {

    private ShoppingService shoppingService = new ShoppingServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取当前登录账号的id
        int uid =(int) req.getSession().getAttribute("uid");

        //查询指定用户名的购物车
        List<Buycar> buycarList = shoppingService.findAllBuycar(uid);

        //System.out.println("buycarList = " + buycarList);
        req.setAttribute("buycarList", buycarList);
        req.getRequestDispatcher("buyCar.jsp").forward(req,resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
