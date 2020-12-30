package cn.echo.impl;

import cn.echo.dao.ProductinfoDao;
import cn.echo.pojo.Productinfo;
import cn.echo.utli.DBCPUtil;
import java.util.List;

/**
 * @Package: cn.echo.impl
 * @Author: zhangjiangnan
 * @CreateTime: 2020/12/24 09:44
 * @Description:
 **/
public class ProductinfoDaoImpl implements ProductinfoDao {
    @Override
    public List<Productinfo> recommended() {
        String sql = "select * from t_productinfo where commend = 1";
        return DBCPUtil.listAll(sql, Productinfo.class);
    }

    @Override
    public List<Productinfo> classify(int id) {
        String sql = "select * from t_productinfo where categoryid = " + id;
        return DBCPUtil.listAll(sql, Productinfo.class);
    }

    @Override
    public List<Productinfo> allProductinfo() {
        String sql ="select * from t_productinfo";
        return DBCPUtil.listAll(sql, Productinfo.class);
    }

    @Override
    public Productinfo selectProductinfoById(int id) {
        String sql = "select * from t_productinfo where id =" + id;
        return DBCPUtil.getById(sql,Productinfo.class);
    }

}
