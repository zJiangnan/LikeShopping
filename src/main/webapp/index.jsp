<%--
  Created by IntelliJ IDEA.
  User: zhangjiangnan
  Date: 2020/12/21
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>喜歡你鸭(>^ω^<)❤️🧡💛</title>
    <link rel="icon" href="img/title.png">
    <link rel="stylesheet" href="layui/css/layui.css"/>
    <script src="js/jquery/jquery-2.1.4.min.js" type="text/javascript"></script>
    <style>
        .cls {
            margin-top: 10px;
        }
    </style>
</head>
<body class="layui-layout-body" style="overflow-x:hidden;overflow-y:hidden">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">
            <img src="img/likelogo.png" style="width: 127px;height: 50px" alt="logo">
        </div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="index.jsp">首页</a></li>
            <li class="layui-nav-item"><a href="productClassifyServlet?method=recommended" target="content">热销</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它</a>
                <dl class="layui-nav-child">
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul
                class="layui-nav layui-col-md3 layui-col-md-offset7">
            <li
                    class="layui-nav cls"><input class="layui-input" type="text" name="sousuo"
                                                 placeholder="输入看看有没有你中意的"></li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
            <a href="login.jsp">登陆</a>
            </li>
            <li class="layui-nav-item"><a href="login.jsp">注册</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <%--                recommendedServlet--%>
                <li class="layui-nav-item"><a href="productClassifyServlet?method=recommended" target="content">推荐喜歡
                </a></li>
                <li class="layui-nav-item"><a href="productClassifyServlet?method=clothing" target="content">服装</a></li>
                <li class="layui-nav-item"><a href="productClassifyServlet?method=shoes" target="content">鞋子</a></li>
                <li class="layui-nav-item"><a href="productClassifyServlet?method=engineering" target="content">电子科技</a></li>
                <li class="layui-nav-item"><a href="productClassifyServlet?method=cosmetics" target="content">化妆品</a></li>
                <li class="layui-nav-item"><a href="productClassifyServlet?method=allProductinfo" target="content">全部商品</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <iframe name="content" src="homes.html" scrolling="no" border="0" frameborder="no" height="1800px"
                width="100%"></iframe>
    </div>
</div>
<script src="layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;
    });
</script>
</body>
</html>