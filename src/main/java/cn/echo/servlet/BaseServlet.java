package cn.echo.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Package: cn.echo.servlet
 * @Author: zhangjiangnan
 * @CreateTime: 2020/12/25 14:37
 * @Description:
 **/
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         // 1、请求地址中需要传递  method   bookServlet?method=listBook
        String method =  req.getParameter("method");
         // 2、通过反射 调用 对应的 方法名
         //根据方法名 获取 方法的Method对象
        try {
            Method m = this.getClass().getMethod(method,
                    HttpServletRequest.class,HttpServletResponse.class);
            // 调用该方法
            Object retObj =  m.invoke(this ,req,resp);
            //如果方法有返回值  则返回的是指定的 url路径  这里 使用转发
            if(retObj !=null){
                // 获取目标地址
                String target = retObj.toString();
                // 请求前缀 "redirect:"  如果前缀是它开头，则使用重定向 ，否则都是转发
                String prefix = "redirect:";    // redirect:index.jsp
                if(target.startsWith(prefix)){
                    //重定向
                    resp.sendRedirect(target.replace(prefix,""));
                }else{
                    //转发
                    req.getRequestDispatcher(target).forward(req,resp);
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
