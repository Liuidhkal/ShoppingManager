package com.easthome.MyFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter({"/home.jsp","/goods.jsp","/buyCar.jsp","/buycar","/goods"})
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String username =(String) req.getSession().getAttribute("username");
        if (username == null){
            //说明未登录，跳转到登录界面
            req.setAttribute("message","请您先登录！");
            req.getRequestDispatcher("login.jsp").forward(req,servletResponse);
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }
}
