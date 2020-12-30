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
 * @CreateTime: 2020/12/23 19:23
 * @Description:个人信息修改服务
 **/
@WebServlet("/userinfoServlet")
public class UserInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer cus = new Customer();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
//        先将Session中的用户对象给cus表示当前用户
        cus = (Customer) req.getSession().getAttribute("uname");
        String password = req.getParameter("password");
        if (password != null && password != "") {
            cus.setPassword(password);
        }
        String realname = req.getParameter("realname");
        if (realname != null && realname != ""){
            cus.setRealname(realname);
        }
        String address = req.getParameter("address");
        if (address != null && address != "") {
            cus.setAddress(address);
        }
        String email = req.getParameter("email");
        if (email != null && email != "") {
            cus.setAddress(address);
        }
        String mobile = req.getParameter("mobile");
        if (mobile != null && mobile != "") {
            cus.setMobile(mobile);
        }
        CustomerDao customerDao = new CustomerDaoImpl();
        int i = customerDao.updateUserInfo(cus);
        if (i != 0){
            out.print("ok");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
