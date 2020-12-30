package cn.echo.servlet;

import cn.echo.dao.CustomerDao;
import cn.echo.dao.ShoppingcarDao;
import cn.echo.impl.CustomerDaoImpl;
import cn.echo.impl.ShoppingcarDaoImpl;
import cn.echo.pojo.Customer;
import cn.echo.pojo.Productinfo;

import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Package: cn.echo.servlet
 * @Author: zhangjiangnan
 * @CreateTime: 2020/12/22 14:29
 * @Description:登陆服务
 **/
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        设置编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
//        返回参数
        PrintWriter out = resp.getWriter();
//        获取前台表单提交数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");
//        将用户数据给数据库查询是否有对应数据
        CustomerDao customerDao = new CustomerDaoImpl();
        Customer cus = (Customer) customerDao.login(username, password);
        /*这里获取是在页面第一次加载购物车时显示用户购物车一共有多少个商品*/
        //        查询个人的购物车里的商品信息集合
        ShoppingcarDao shoppingcarDao = new ShoppingcarDaoImpl();
        List<Productinfo> productinfoList = shoppingcarDao.selectUserCarcon((int) cus.getId());
        //查询总记录数
        int count = productinfoList.size();
//        将查询到的记录总数(记录总数就是个人用户购物车里的商品数)Session容器中
        req.getSession().setAttribute("userprocount", count);
//        如果存在此数据就成功跳转
        if (cus.getUsername() != null) {
                System.out.println("登陆成功");
            req.getSession().setAttribute("uname", cus);
            req.getSession().setMaxInactiveInterval(60 * 120);
            out.print("ok");
        } else {
//            System.out.println("登陆失败");
            out.print("no");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
