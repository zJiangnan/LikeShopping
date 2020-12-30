<%--
  Created by IntelliJ IDEA.
  User: zhangjiangnan
  Date: 2020/12/23
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>个人信息❤️🧡💛喜歡</title>
    <link rel="icon" href="img/title.png">
    <link rel="stylesheet" href="layui/css/layui.css"/>
    <script src="js/jquery/jquery-2.1.4.min.js" type="text/javascript"></script>
    <style>
        .cls{
            margin-top: 120px;
        }
        body{
            background-image: url("img/loginbgck.jpg");
            background-size: 100%;
        }
    </style>
</head>
<body>
<div class="layui-container ">
    <div class="layui-row  cls  layui-col-md4 layui-col-md-offset4">
        <div class="layui-tab layui-tab-brief ">
            <h2 style="color: red">${loginmsg}</h2>
            <ul class="layui-tab-title">
                <li class="layui-this" id="login">个人信息</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <div class="layui-form-item"><h3>❤️ 个人信息修改输入新的值点击修改按钮即可❤️</h3></div>
                    <!--  登录表单 -->
                    <form class="layui-form layui-form-pane" id="infoform">
                        <span style="color: rgb(251, 86, 85)">不可更改用户名哦，亲🤪</span>
                        <div class="layui-form-item">
                            <label class="layui-form-label">用户名</label>
                            <div class="layui-input-block">
                                <input readonly name="username" placeholder="${uname.username}" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">密码</label>
                            <div class="layui-input-block">
                                <input type="password" name="password" class="layui-input" autocomplete="off"
                                       placeholder="请输入新的密码"/>

                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">真实姓名</label>
                            <div class="layui-input-block">
                                <input type="text" name="realname" autocomplete="off" placeholder="${uname.realname}"
                                       class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">地址</label>
                            <div class="layui-input-block">
                                <input type="text" name="address" autocomplete="off" placeholder="${uname.address}"
                                       class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">邮箱</label>
                            <div class="layui-input-block">
                                <input type="text" name="email" autocomplete="off" placeholder="${uname.email}"
                                       class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">手机号</label>
                            <div class="layui-input-block">
                                <input type="text" name="mobile" autocomplete="off" placeholder="${uname.mobile}"
                                       class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <button id="infobtn" class="layui-btn layui-col-lg-offset4">修改个人信息</button>
                        </div>

                    </form>
                </div>

            </div>
        </div>

    </div>
</div>
</body>
<script>
    $(function () {
       $("#infobtn").click(function () {
           $.ajax({
               type:"get",
               url:"userinfoServlet",
               data:$("#infoform").serialize(),
               success:function (resp) {
                   if(resp=="ok"){
                       // alert("你的消息已成功保存了鸭🥳！！！！");
                       var su = confirm("你的消息已成功保存了鸭🥳！！！！");
                       if (su){
                           window.location = "user.jsp";
                       }
                   }else{
                       alert("修改失败，请稍后再试哦🤨");
                   }
               },
               error:function () {
                   alert("系统错误");
               }
           });
           //    防止表单自动提交
           return false;
       });
    });
</script>
</html>
