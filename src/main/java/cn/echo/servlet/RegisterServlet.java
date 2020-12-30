package cn.echo.servlet;

import cn.echo.dao.CustomerDao;
import cn.echo.impl.CustomerDaoImpl;
import cn.echo.pojo.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Package: cn.echo.servlet
 * @Author: zhangjiangnan
 * @CreateTime: 2020/12/23 10:42
 * @Description:注册服务
 **/
@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer cus = new Customer();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        cus.setUsername(req.getParameter("username"));
        cus.setPassword(req.getParameter("password"));
        cus.setRealname(req.getParameter("realname"));
        cus.setAddress(req.getParameter("address"));
        cus.setEmail(req.getParameter("email"));
        cus.setMobile(req.getParameter("mobile"));
//        System.out.println(cus);
        CustomerDao customerDao = new CustomerDaoImpl();
        int i = customerDao.reg(cus);
        System.out.println("影响行数："+i);
        req.setAttribute("username", cus.getUsername());
        if (i != 0){
            System.out.println("注册成功");
            req.getSession().setAttribute("uname", cus);
            out.print("ok");
        }else {
            System.out.println("注册失败");
            out.print("on");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
