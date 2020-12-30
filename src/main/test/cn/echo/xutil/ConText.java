package cn.echo.xutil;

import cn.echo.dao.CustomerDao;
import cn.echo.dao.ProductinfoDao;
import cn.echo.dao.RootDao;
import cn.echo.dao.ShoppingcarDao;
import cn.echo.impl.CustomerDaoImpl;
import cn.echo.impl.ProductinfoDaoImpl;
import cn.echo.impl.RootDaoImpl;
import cn.echo.impl.ShoppingcarDaoImpl;
import cn.echo.pojo.*;
import cn.echo.utli.DBCPUtil;
import org.junit.Test;

import java.util.List;

/**
 * @Package: cn.echo.xutil
 * @Author: zhangjiangnan
 * @CreateTime: 2020/12/22 14:35
 * @Description:
 **/
public class ConText {

    /**
     * 测试添加用户
     */
    @Test
    public void testCon() {
        CustomerDao customerDao = new CustomerDaoImpl();
        Customer c = new Customer();
        c.setUsername("qwer");
        c.setPassword("123");
        c.setRealname("李四");
        c.setAddress("湖北武汉");
        c.setEmail("123@163.com");
        c.setMobile("14528796645");
        int k = customerDao.reg(c);
        System.out.println(k);
    }

    /**
     * 测试查询管理员用户
     */
    @Test
    public void testRoot() {
        RootDao rootDao = new RootDaoImpl();
        Root root = rootDao.selectUser("123", "123");
        System.out.println(root);
    }

    /**
     * 测试查询推荐商品集合
     */
    @Test
    public void recommended () {
        ProductinfoDao productinfoDao = new ProductinfoDaoImpl();
        List<Productinfo> recommended = productinfoDao.recommended();
        for (Productinfo p : recommended) {
            System.out.println(p);
        }
    }

    /**
     * 测试查询单个商品
     */
    @Test
    public void selectProductinfoById () {
        ProductinfoDao productinfoDao = new ProductinfoDaoImpl();
        Productinfo productinfo = productinfoDao.selectProductinfoById(1);
        System.out.println(productinfo);
    }

    /**
     * 测试购物车查找商品信息
     */
    @Test
    public void selectUserCar() {
        ShoppingcarDao shoppingcarDao = new ShoppingcarDaoImpl();
        List<Productinfo> productinfoList = shoppingcarDao.selectUserCarcon(1);
        for (Productinfo productinfo : productinfoList) {
            System.out.println(productinfo);
        }
    }

    /**
     * 测试查询所有商品
     */
    @Test
    public void allProductinfo(){
        ProductinfoDao productinfoDao = new ProductinfoDaoImpl();
        List<Productinfo> productinfoList = productinfoDao.allProductinfo();
        for (Productinfo productinfo : productinfoList) {
            System.out.println(productinfo);
        }
    }

    /**
     * 测试分类数据
     */
    @Test
    public void category() {
        ProductinfoDao productinfoDao = new ProductinfoDaoImpl();
        List<Productinfo> productinfoList = productinfoDao.classify(2);
        for (Productinfo productinfo : productinfoList) {
            System.out.println(productinfo);
        }
    }

    /**
     * 测试购物车添加功能
     */
    @Test
    public void addProductCar() {
        ShoppingcarDao shoppingcarDao = new ShoppingcarDaoImpl();
        Productinfo productinfo = new Productinfo();
        productinfo.setName("Dior 999 色号口红");
        productinfo.setSellprice(120.00);
        int i = shoppingcarDao.addProductCar(5, 3, productinfo);
        System.out.println(i);
    }

    /**
     * 测试购物车分页
     */
    @Test
    public void selectshoppingcar(){
        ShoppingcarDao shoppingcarDao = new ShoppingcarDaoImpl();
        List<Productinfo> productinfos = shoppingcarDao.selectUserCar(1, 1, 5);
        for (Productinfo productinfo : productinfos) {
            System.out.println(productinfo);
        }
    }

}
