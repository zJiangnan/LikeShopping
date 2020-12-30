package cn.echo.servlet;

import cn.echo.dao.ShoppingcarDao;
import cn.echo.impl.ShoppingcarDaoImpl;
import cn.echo.pojo.Customer;
import cn.echo.pojo.Productinfo;
import cn.echo.utli.JsonDateValueProcessor;
import cn.echo.utli.PageUtil;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @Package: cn.echo.servlet
 * @Author: zhangjiangnan
 * @CreateTime: 2020/12/24 15:31
 * @Description: 个人购物车配置类
 **/
@WebServlet("/userShoppingCarServlet")
public class UserShoppingCarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        设置编码格式
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
//        获取当前用户
        Customer cus = (Customer) req.getSession().getAttribute("uname");
        //获取当前页
        String currpage = req.getParameter("page");
        if (currpage == null || currpage == "") {
            currpage = "1";
        }
        //获取一页显示行数
        String limit = req.getParameter("limit");
        if (limit == null || limit == "") {
            limit = "5";
        }
//        测试是否获取到值
//        System.out.println(currpage + "----" + limit);
        //获取商品ID
        String id = req.getParameter("id");
        //获取商品名字
//        测试是否获取到分页参数
//        System.out.println(currpage);
//        System.out.println(limit);
//        查询个人的购物车里的商品信息集合
        ShoppingcarDao shoppingcarDao = new ShoppingcarDaoImpl();
//        查询总记录数
        List<Productinfo> productinfos = shoppingcarDao.selectUserCarcon((int) cus.getId());
        int size = productinfos.size();
        List<Productinfo> productinfoList = shoppingcarDao.selectUserCar(((int)cus.getId()),
                Integer.parseInt(currpage), Integer.parseInt(limit));
//        测试数据
        /*for (Productinfo productinfo : productinfoList) {
            System.out.println(productinfo);
        }*/
        //查询总记录数
        int count = size;
        // 创建前端需要的Page JSON对象
        PageUtil<Productinfo> page = new PageUtil<>();
        page.setCode(0);
        page.setMsg("数据响应成功");
//        设置获取到的商品集合
        page.setData(productinfoList);
//        设置集合里的对象记录数
        page.setCount(count);
//        设置一页显示的行数
        page.setLimit(Integer.parseInt(limit));
        // 将对象转成 json格式
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor());
        //日期转换
        JSONObject obj = JSONObject.fromObject(page, jsonConfig);
//        测试转换后的JSON格式对象的数据是否正确
//        System.out.println("obj：" + obj.toString());
//        将转换好的数据给前台
        out.print(obj.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
