package cn.echo.dao;

import cn.echo.pojo.Customer;
import com.sun.org.apache.xerces.internal.impl.io.UCSReader;

/**
 * @Package: cn.echo.dao
 * @Author: zhangjiangnan
 * @CreateTime: 2020/12/22 14:46
 * @Description:
 **/
public interface CustomerDao {
    /**
     * 用户登陆---查询个人信息
     * 返回一个用户
     * @return
     */
    public Customer login(String username, String password);

    /**
     * 用户注册
     * @param cus   用户对象
     * @return
     */
    public int reg(Customer cus);

    /**
     * 修改用户
     * @param cus   用户对象
     * @return
     */
    public int updateUserInfo(Customer cus);

}
