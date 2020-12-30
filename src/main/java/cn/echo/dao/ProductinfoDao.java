package cn.echo.dao;

import cn.echo.pojo.Productinfo;

import java.util.List;

/**
 * @Package: cn.echo.dao
 * @Author: zhangjiangnan
 * @CreateTime: 2020/12/24 09:41
 * @Description:
 **/
public interface ProductinfoDao {

    /**
     * 查询推荐商品
     * @return
     */
    public List<Productinfo> recommended();

    /**
     * 查询分类商品
     * @param id    商品分类的服装类ID
     * @return
     */
    public List<Productinfo> classify(int id);

    /**
     * 查询所有商品
     * @return
     */
    public List<Productinfo> allProductinfo();

    /**
     * 根据ID查询商品信息
     * @param id    商品ID
     * @return  返回商品对象
     */
    public Productinfo selectProductinfoById(int id);
}
