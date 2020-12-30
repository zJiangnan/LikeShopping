package cn.echo.servlet;

import cn.echo.dao.ProductinfoDao;
import cn.echo.dao.ShoppingcarDao;
import cn.echo.impl.ProductinfoDaoImpl;
import cn.echo.impl.ShoppingcarDaoImpl;
import cn.echo.pojo.Customer;
import cn.echo.pojo.Productinfo;

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
 * @CreateTime: 2020/12/25 14:40
 * @Description:
 **/
@WebServlet("/likeShopServlet")
public class LikeShopServlet extends BaseServlet {
    //    将物品添加购物车
    public void addShoppingCar(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
//        获取前台传递过来的商品ID
        Integer productid = Integer.parseInt(req.getParameter("productid"));
//          测试输出商品ID
//        System.out.println(productid);
//        通过商品ID找到商品信息对象
        ProductinfoDao productinfoDao = new ProductinfoDaoImpl();
        Productinfo productinfo = productinfoDao.selectProductinfoById(productid);
//        测试从database获取到的商品信息是否存在
//        System.out.println(productinfo);
//        获取登陆用户的对象
        Customer uname = (Customer) req.getSession().getAttribute("uname");
//        判断是否获取到了用户信息如果没有则没登陆
        if (uname == null || uname.getId() == 0) {
            out.print("no");
        } else {
//        测试是否获取到了登陆用户的信息
//        System.out.println(uname);
//      通过购物车功能实现类实现添加商品至购物车
            ShoppingcarDao shoppingcarDao = new ShoppingcarDaoImpl();
            int k = shoppingcarDao.addProductCar((int) uname.getId(), productid, productinfo);
//        输出测试是否添加成功
//        System.out.println("添加购物车受影响行：" + k);
            if (k == 0) {
                out.print("no");
            }
            /*添加够成功后需要更新用户购物车商品数量*/
            //        获取当前用户
            Customer cus = (Customer) req.getSession().getAttribute("uname");
            //        查询个人的购物车里的商品信息集合
            List<Productinfo> productinfoList = shoppingcarDao.selectUserCarcon((int) cus.getId());
            //查询总记录数
            int count = productinfoList.size();
            //        更新购物车商品数量
            req.getSession().setAttribute("userprocount", count);
            out.print("ok");
        }
    }
}
