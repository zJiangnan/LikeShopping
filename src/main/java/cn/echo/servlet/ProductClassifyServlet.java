package cn.echo.servlet;

import cn.echo.dao.ProductinfoDao;
import cn.echo.impl.ProductinfoDaoImpl;
import cn.echo.pojo.Productinfo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Package: cn.echo.servlet
 * @Author: zhangjiangnan
 * @CreateTime: 2020/12/26 10:07
 * @Description:商品分类展示的配置类
 **/
@WebServlet("/productClassifyServlet")
public class ProductClassifyServlet extends BaseServlet {

    /**
     * 推荐商品的配置方法
     * @param req
     * @param resp
     * @throws IOException
     */
    public void recommended(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //        更改编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
//        创建实体类的dao的实现类对象
        ProductinfoDao productinfoDao = new ProductinfoDaoImpl();
//        从数据库获取数据
        List<Productinfo> recommended = productinfoDao.recommended();
//        测试数据是否成功获取到
//        for (Productinfo productinfo : recommended) {
//            System.out.println(productinfo);
//        }
//        将获取到的商品集合存放在Session中
        req.getSession().setAttribute("recommended", recommended);
//        重定向到新的页面展示数据
        resp.sendRedirect("recommended.jsp");
    }

    /**
     * 服装商品的配置方法
     * @param req
     * @param resp
     * @throws IOException
     */
    public void clothing(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //        设置编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
//        创建商品功能的实体类并获取数据库数据
        ProductinfoDao productinfoDao = new ProductinfoDaoImpl();
        List<Productinfo> classify = productinfoDao.classify(2);
//        将集合对象存储到Session中
        req.getSession().setAttribute("clothings", classify);
//        for (Productinfo productinfo : classify) {
//            System.out.println(productinfo);
//        }
//        重定向到新的页面
        resp.sendRedirect("clothing.jsp");
    }

    /**
     * 鞋子商品查询的配置方法
     * @param req
     * @param resp
     * @throws IOException
     */
    public void shoes(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //        设置编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
//        创建商品功能的实体类并获取数据库数据
        ProductinfoDao productinfoDao = new ProductinfoDaoImpl();
        List<Productinfo> classify = productinfoDao.classify(4);
//        将获取到的数据集合存入Session中
        req.getSession().setAttribute("shoes", classify);
        /*for (Productinfo productinfo : classify) {
            System.out.println(productinfo);
        }*/
//        重定向到新的页面
        resp.sendRedirect("shoes.jsp");
    }

    /**
     * 电子科技分类展示配置方法
     * @param req
     * @param resp
     * @throws IOException
     */
    public void engineering(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //        设置编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
//        从数据获取数据集合
        ProductinfoDao productinfoDao = new ProductinfoDaoImpl();
        List<Productinfo> classify = productinfoDao.classify(11);
//        将集合存入Session中
        req.getSession().setAttribute("engineering", classify);
//        for (Productinfo productinfo : classify) {
//            System.out.println(productinfo);
//        }
//        重定向到新的页面
        resp.sendRedirect("engineering.jsp");
    }

    /**
     * 化妆品分类展示配置方法
     * @param req
     * @param resp
     * @throws IOException
     */
    public void cosmetics(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //        设置编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
//        从后台获取数据集合
        ProductinfoDao productinfoDao = new ProductinfoDaoImpl();
        List<Productinfo> classify = productinfoDao.classify(6);
        req.getSession().setAttribute("cosmetics", classify);
//        for (Productinfo productinfo : classify) {
//            System.out.println(productinfo);
//        }
//        重定向
        resp.sendRedirect("cosmetics.jsp");
    }

    /**
     * 查询所有商品配置方法
     * @param req
     * @param resp
     * @throws IOException
     */
    public void allProductinfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //        设置编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
//        获取商品功能对象
        ProductinfoDao productinfoDao = new ProductinfoDaoImpl();
//        查询所有商品信息
        List<Productinfo> productinfoList = productinfoDao.allProductinfo();
//        将数据存入Session中
        req.getSession().setAttribute("allProductinfo", productinfoList);
//        测试查询到的输出的数据
//        for (Productinfo productinfo : productinfoList) {
//            System.out.println(productinfo);
//        }
//        重定向新页面
        resp.sendRedirect("allproductinfo.jsp");
    }
}
