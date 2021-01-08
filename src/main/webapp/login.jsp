<%--
  Created by IntelliJ IDEA.
  User: zhangjiangnan
  Date: 2020/12/21
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <title>喜歡购物-登陆❤️🧡💛注册</title>
    <link rel="icon" href="img/title.png">
    <link href="layui/css/layui.css" rel="stylesheet"/>
    <script src="js/jquery/jquery-2.1.4.min.js" type="text/javascript"></script>
    <style>
        .cls {
            margin-top: 100px;
        }
        body{
            background-image: url("img/loginbgck.jpg");
        }
    </style>
</head>
<body>
<div class="layui-container ">
    <div class="layui-row  cls  layui-col-md4 layui-col-md-offset4">
        <div class="layui-tab layui-tab-brief ">
            <h2 style="color: red">${loginmsg}</h2>
            <ul class="layui-tab-title">
                <li class="layui-this" id="login">登录</li>
                <li>注册</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <!--  登录表单 -->
                    <form class="layui-form layui-form-pane" id="loginform">

                        <div class="layui-form-item">
                            <label class="layui-form-label"><i class="layui-icon layui-icon-username"></i></label>
                            <div class="layui-input-block">
                                <input type="text" name="username" autocomplete="off" placeholder="请输入用户名"
                                       class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label"><i class="layui-icon layui-icon-password"></i></label>
                            <div class="layui-input-block">
                                <input type="password" name="password" class="layui-input" autocomplete="off"
                                       placeholder="请输入密码"/>

                            </div>
                        </div>
<%--                        验证码--%>
                        <div class="layui-form-item">
                            <span style="color: red"><h2>${loginmsg}</h2></span>
                            <button id="loginbtn" class="layui-btn">登录</button>
                            <a href="#" style="margin-left: 30px;">忘记密码</a>
                        </div>
                    </form>
                </div>
                <div class="layui-tab-item">
                    <!--  注册表单 -->
                    <form class="layui-form layui-form-pane" id="regform">

                        <div class="layui-form-item">
                            <label class="layui-form-label"><i class="layui-icon layui-icon-username"></i></label>
                            <div class="layui-input-block">
                                <input type="text" name="username" autocomplete="off" placeholder="请输入用户名"
                                       class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label"><i class="layui-icon layui-icon-password"></i></label>
                            <div class="layui-input-block">
                                <input type="password" name="password" class="layui-input" autocomplete="off"
                                       placeholder="请输入密码"/>

                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label"><i class="layui-icon layui-icon-friends"></i></label>
                            <div class="layui-input-block">
                                <input type="text" name="realname" autocomplete="off" placeholder="请输入真实姓名"
                                       class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label"><i class="layui-icon layui-icon-home"></i></label>
                            <div class="layui-input-block">
                                <input type="text" name="address" autocomplete="off" placeholder="请输入您的地址"
                                       class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label"><i class="layui-icon layui-icon-email"></i></label>
                            <div class="layui-input-block">
                                <input type="text" name="email" autocomplete="off" placeholder="请输入邮箱"
                                       class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label"><i class="layui-icon layui-icon-cellphone"></i></label>
                            <div class="layui-input-block">
                                <input type="text" name="mobile" autocomplete="off" placeholder="请输入手机号"
                                       class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <button id="regbtn" class="layui-btn">注册</button>
                            <a href="#login" style="margin-left: 30px;">已有账号？登陆！</a>
                        </div>

                    </form>
                </div>

            </div>
        </div>

    </div>
</div>
</body>
<script src="layui/layui.js"></script>
<script>
    //注意：选项卡 依赖 element 模块，否则无法进行功能性操作
    layui.use(['layer', 'form', 'element'], function () {
        var element = layui.element;
        var layer = layui.layer;
        var form = layui.form;
    });
    $(function () {
        //登陆
        $("#loginbtn").click(function () {
            $.ajax({
                type:"get",
                url:"loginServlet",
                data:$("#loginform").serialize(),
                success:function (resp) {
                    if(resp=="ok"){
                        location.href="user.jsp";
                    }else if(resp == "no"){
                        alert("用户名或密码错误，没有账号？试试注册🥳");
                    }
                },
                error:function () {
                    alert("用户名或密码错误");
                }
            });
        //    防止表单自动提交
            return false;
        });
        // 注册
        $("#regbtn").click(function () {
            $.ajax({
                type:"get",
                url:"registerServlet",
                data:$("#regform").serialize(),
                success:function (resp) {
                    if(resp == "ok") {
                        location.href = "user.jsp";
                    }else{
                        alert("系统错误！！！");
                    }
                },
                error:function (resp){
                    alert("异常错误");
                }
            });
            //    防止表单自动提交
            return false;
        });
    });
</script>
</html>
</body>
</html>
