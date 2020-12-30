package cn.echo.impl;

import cn.echo.dao.CustomerDao;
import cn.echo.pojo.Customer;
import cn.echo.utli.DBCPUtil;

/**
 * @Package: cn.echo.impl
 * @Author: zhangjiangnan
 * @CreateTime: 2020/12/22 14:52
 * @Description:
 **/
public class CustomerDaoImpl implements CustomerDao {
    @Override
    public Customer login(String username, String password) {
        String sql = "SELECT * FROM t_customer where username='" + username + "' and password='" + password + "'";
        return DBCPUtil.getById(sql, Customer.class);
    }

    @Override
    public int reg(Customer cus) {
        String sql = "INSERT INTO t_customer VALUES(null, ?, ?, ?, ?, ?, ?)";
        return DBCPUtil.executeUpdate(sql,cus.getUsername(), cus.getPassword(), cus.getRealname(), cus.getAddress(),
                cus.getEmail(), cus.getMobile());
    }

    @Override
    public int updateUserInfo(Customer cus) {
        String sql = "UPDATE t_customer SET password=?, realname=?, address=?, email=?, mobile=? where id = ?";
        return DBCPUtil.executeUpdate(sql,cus.getPassword(), cus.getRealname(), cus.getAddress(), cus.getEmail(),
                cus.getMobile(), cus.getId());
    }
}
