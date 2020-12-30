package cn.echo.impl;

import cn.echo.dao.ProductinfoDao;
import cn.echo.dao.ShoppingcarDao;
import cn.echo.pojo.Productinfo;
import cn.echo.pojo.Shoppingcar;
import cn.echo.utli.DBCPUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package: cn.echo.impl
 * @Author: zhangjiangnan
 * @CreateTime: 2020/12/24 14:45
 * @Description:
 **/
public class ShoppingcarDaoImpl implements ShoppingcarDao {

    @Override
    public List<Productinfo> selectUserCarcon(int id) {
        String sql = "SELECT * FROM t_shoppingcar WHERE customerid = " + id;
        List<Shoppingcar> shoppingcarList = DBCPUtil.listAll(sql, Shoppingcar.class);
//        创建商品功能的实现累
        ProductinfoDao productinfoDao = new ProductinfoDaoImpl();
//        创建商品集合对象用于存储通过购物车商品ID获取商品详细信息
        List<Productinfo> productinfoList = new ArrayList<>();
//        循环遍历购物车里的商品ID通过ID获取商品信息
        for (Shoppingcar shoppingcar : shoppingcarList) {
            long productid = shoppingcar.getProductid();
            productinfoList.add(productinfoDao.selectProductinfoById((int) productid));
        }
        return productinfoList;
    }

    @Override
    public List<Productinfo> selectUserCar(int id, int page, int limit) {
        String sql =
                "SELECT * FROM t_shoppingcar WHERE customerid = '" + id + "' limit " + (page - 1) * limit + "," + limit;
        List<Shoppingcar> shoppingcarList = DBCPUtil.listAll(sql, Shoppingcar.class);
//        创建商品功能的实现累
        ProductinfoDao productinfoDao = new ProductinfoDaoImpl();
//        创建商品集合对象用于存储通过购物车商品ID获取商品详细信息
        List<Productinfo> productinfoList = new ArrayList<>();
//        循环遍历购物车里的商品ID通过ID获取商品信息
        for (Shoppingcar shoppingcar : shoppingcarList) {
            long productid = shoppingcar.getProductid();
            productinfoList.add(productinfoDao.selectProductinfoById((int) productid));
        }
        return productinfoList;
    }

    @Override
    public int addProductCar(int uid, int pid, Productinfo pro) {
        String sql = "INSERT INTO t_shoppingcar VALUES(null, ?, ?, ?, ?, ?)";
        return DBCPUtil.executeUpdate(sql, pid, uid, pro.getSellprice(), 1, pro.getName());
    }


}
