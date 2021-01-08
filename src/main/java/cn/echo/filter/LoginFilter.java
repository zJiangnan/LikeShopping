package cn.echo.filter;

import cn.echo.pojo.Customer;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Package: cn.echo.filter
 * @Author: zhangjiangnan
 * @CreateTime: 2021/1/8 08:53
 * @Description:
 **/
//@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //将请求对象转换HttpServletRequest
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        //有些请求需要自定放行，比如登录，注册，页面样式等功能
        String uri = req.getRequestURI();
        System.out.println("uri---" + uri);
        if (uri.matches(".*login.*") || uri.matches(".*index.*")
                || uri.matches(".*register.*")
                || uri.contains(".css") || uri.matches(".*\\.js$")
                || uri.contains(".jpg") || uri.contains(".png")) {
            System.out.println("放行");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //验证用户是否登录（判断session中是否存在user）
        Customer uname = (Customer) req.getSession().getAttribute("uname");
        System.out.println(uname);
        if (uname.getUsername() != null) {
            //说明已经登录，直接放行
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            //没有登录，让请求跳转到登录页面
            req.getRequestDispatcher("login.jsp").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
