package cn.echo.dao;

import cn.echo.pojo.Root;

/**
 * @Package: cn.echo.dao
 * @Author: zhangjiangnan
 * @CreateTime: 2020/12/23 11:21
 * @Description:
 **/
public interface RootDao {
    public Root selectUser(String name, String password);
}
