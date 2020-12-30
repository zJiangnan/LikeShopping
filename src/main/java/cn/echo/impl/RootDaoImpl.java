package cn.echo.impl;

import cn.echo.dao.RootDao;
import cn.echo.pojo.Root;
import cn.echo.utli.DBCPUtil;

/**
 * @Package: cn.echo.impl
 * @Author: zhangjiangnan
 * @CreateTime: 2020/12/23 11:23
 * @Description:
 **/
public class RootDaoImpl implements RootDao {
    @Override
    public Root selectUser(String name, String password) {
        String sql = "select * from t_root where name='" + name + "' and password='" + password + "'";
        return DBCPUtil.getById(sql,Root.class);
    }
}
