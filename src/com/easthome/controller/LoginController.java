package com.easthome.controller;

import com.easthome.entity.User;
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
* 实现登录
* */
@WebServlet("/login")
public class LoginController extends HttpServlet {
    private ShoppingService shoppingService = new ShoppingServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取username和password
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //获取数据库 list
        List<User> list = shoppingService.UserPanduan();
        //System.out.println(list);

        for (User user : list){
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                //账号密码正确，跳转到首页

                //将uid存到session中
                req.getSession().setAttribute("uid",user.getId());
                //用户名存入session
                req.getSession().setAttribute("username",username);
                //req.setAttribute("username",user.getUsername());
                req.getRequestDispatcher("home.jsp").forward(req,resp);
            }
        }
        //不正确，返回登录界面
        req.setAttribute("message","账号或密码不正确，请重新输入！");
        req.getRequestDispatcher("login.jsp").forward(req,resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
