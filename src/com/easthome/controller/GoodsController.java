package com.easthome.controller;

import com.easthome.entity.Goods;
import com.easthome.service.ShoppingService;
import com.easthome.service.imp.ShoppingServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/*
* 获取商品列表的信息传输给前端页面
* */
@WebServlet("/goods")
public class GoodsController extends HttpServlet {
    private ShoppingService shoppingService = new ShoppingServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取页数
        int start = Integer.parseInt(req.getParameter("start"));
        //System.out.println(start);
        //获取goods的数量
        int count =(int) shoppingService.goodsByCount();

        //上一页
        int up = start-1 >= 1 ? start-1 : 1;

        //获取总页面
        int pages = count%3 == 0 ? count/3 : count/3+1;

        //下一页
        int next = start+1 <= pages ? start+1 : pages;

        //System.out.println("next = " + next);
        //尾页
        int end = pages;

        HttpSession session = req.getSession();
        session.setAttribute("up",up);
        session.setAttribute("next",next);
        session.setAttribute("end",end);


        List<Goods> listGoods = shoppingService.findByGoods(start);
        //System.out.println(listGoods);

        req.setAttribute("listGoods",listGoods);
        req.getRequestDispatcher("goods.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
