package cn.echo.utli;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * ClassName: DBCPUtil
 * Description:
 * date: 2020/11/25 11:04
 *
 * @author wuyafeng
 * @version 1.0   softeem.com
 */
public class DBCPUtil {
    // BasicDataSource 是 DataSource的实现类
    static DataSource ds=null;
    static{
        // 可以创建数据源对象，设置连接池的连接信息
//        ds = new BasicDataSource();
//        // 设置连接参数
//        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        ds.setUrl("jdbc:mysql://localhost:3306/j2008_db?useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true");
//        ds.setUsername("root");
//        ds.setPassword("123456");
//        ds.setMaxActive(20);


//        try {
//            System.out.println("获取连接对象："+ds.getConnection());
//            System.out.println(" 最大连接数："+ds.getMaxActive());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        //  也通过读配置文件，将配置信息加载
        try {
            // 1、读取配置文件
            InputStream is =DBCPUtil.class.getClassLoader().getResourceAsStream("dbcp.properties");
            Properties prop = new Properties();
            // 2、加载属性文件
            prop.load(is);
            //3、 通过数据源工厂类
            ds = BasicDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取数据源连接对象
     * @return
     */
    public static Connection getConn(){

        try {
            if(ds!=null) {
                return ds.getConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void  closeAll(Connection conn , PreparedStatement ps , ResultSet rs){
        try {
            if(rs!=null) {
                rs.close();
            }
            if(ps!=null) {
                ps.close();
            }
            if(conn!=null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    /**
     *
     *
     */
    /**
     * 对于 添加，删除，修改方法    不同点在于  sql语句不同
     *  参数不同
     * @param sql  sql语句   insert into dept values(?,?,?)
     * @param objs 任意个数任意类型的参数  与sql的？一一对应
     * @return
     */
    public static  int executeUpdate(String sql ,Object ... objs){
        // 获取连接
        Connection conn=null;
        PreparedStatement ps=null;
        // 获取预编译sql执行器
        try {
            conn = DBCPUtil.getConn();
            ps = conn.prepareStatement(sql);
            //设置参数
            if(objs!=null){
                for(int i=0;i<objs.length;i++){
                    // 参数从1开始，数组objs的下标从0开始
                    ps.setObject(i+1 ,objs[i]);
                }
            }
            //执行sql语句
            int count = ps.executeUpdate();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭连接
            DBCPUtil.closeAll(conn,ps,null);
        }
        return 0;
    }

    /**
     * 查询所有的公共方法， 可以自动将结果集映射到 实体类上
     * @param sql  sql语句
     * @param cls  实体类的类型
     * @param <T>  实体类的泛型
     * @return
     */
    public static<T> List<T> listAll(String sql ,Class cls ){
        // 存储结果集的集合list
        List<T> list = new ArrayList<T>();
        Connection conn=null;
        PreparedStatement ps = null;
        ResultSet rs=null;
        try {
            //获取连接
            conn = DBCPUtil.getConn();
            ps = conn.prepareStatement(sql);
            rs  = ps.executeQuery();
            while(rs.next()){
                // 创建这个类型的对象
                Object obj = cls.newInstance();
                // 获取这个类的所有字段个数  获取每一行的元数据
                ResultSetMetaData rsmd = rs.getMetaData();
                int clsCount =rsmd.getColumnCount();
                for(int i=1;i<=clsCount;i++){
                    String columnName = rsmd.getColumnLabel(i);
                    //构建set方法
                    String setMethod =  "set"+columnName.substring(0,1).toUpperCase() + columnName.substring(1);
                    // 调用get方法 获取返回值类型
                    String getMethod = "get"+columnName.substring(0,1).toUpperCase() + columnName.substring(1);
                    Method m1 = cls.getMethod(getMethod,null);

                    //调用方法  方法的参数类型  是 get方法的返回值类型
                    Method m = cls.getMethod(setMethod, m1.getReturnType());
                    // 调用set方法
                    m.invoke(obj,rs.getObject(columnName));

                }
                // 将obj放入 list中
                list.add((T)obj);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }  catch (NoSuchMethodException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBCPUtil.closeAll(conn,ps,rs);
        }

        return list;
    }

    /**
     * 根据id查询一行记录
     * @param sql
     * @param cls
     * @param <T>
     * @return
     */
    public static<T> T getById(String sql ,Class cls){
        List<T> list =  listAll(sql,cls)  ;
        //查询一个集合，取第一行记录
        return list.size()>0 ? list.get(0) : null;
    }


//    public static void main(String[] args) {
//        System.out.println(getConn());
//    }
}
