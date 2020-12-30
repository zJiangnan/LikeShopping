package cn.echo.dao;

import cn.echo.pojo.Customer;
import cn.echo.pojo.Productinfo;
import cn.echo.pojo.Shoppingcar;

import java.util.List;

/**
 * @Package: cn.echo.dao
 * @Author: zhangjiangnan
 * @CreateTime: 2020/12/24 14:43
 * @Description:
 **/
public interface ShoppingcarDao {
    /**
     * 获取个人用户的购物车商品
     * @param id    用户ID
     * @return  返回用户购物中的商品信息
     */
    public List<Productinfo> selectUserCarcon(int id);

    /**
     * 分页查询数据
     * @param id
     * @param page
     * @param limit
     * @return
     */
    public List<Productinfo> selectUserCar(int id, int page, int limit);

    /**
     * 将商品信息添加到个人购物车
     * @param uid   用户ID
     * @param pid   商品ID
     * @pro         商品信息
     * @return      返回添加行数
     */
    public int addProductCar(int uid, int pid, Productinfo pro);
}
