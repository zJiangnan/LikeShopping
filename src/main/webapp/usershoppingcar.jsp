<%--
  Created by IntelliJ IDEA.
  User: zhangjiangnan
  Date: 2020/12/26
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>${uname.realname}购物车</title>
    <link rel="icon" href="img/title.png">
    <link rel="stylesheet" href="layui/css/layui.css">
</head>
<body>
<div style="height: 70px;width: 100%;"></div>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-group">
        // lay-event 表示触发事件的 唯一标识
        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
        <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
        <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
    </div>
</script>
<div class="layui-container">
    <div class="layui-card">
        <div class="layui-card-body" style="position: relative;">
            <table id="mytab" lay-filter="mytab">
                <h1 style="color: red">${uname.realname}的购物车🥳🥳🥳<span class="layui-badge">${userprocount}</span></h1>
<%--                右下角购买--%>
            </table>
                <div style="position: absolute;left: 950px;">
                    <span style="font-size: 28px">总金额<span id="m" style="color: red;font-size: 21px">123</span>元</span>
                    <button class="layui-btn layui-btn-lg layui-btn-danger" lay-event="buy">立即购买</button>
                </div>
        </div>
    </div>
</div>
</body>
<script src="js/jquery/jquery-2.1.4.js"></script>
<script src="layui/layui.js"></script>
<script>
    // var date = new Date();
    // layui 是拿来即用，需要在页面上定义 使用的 模块组件
    layui.use(['layer', 'form', 'table', 'laydate'], function () {
        // 这里的layer  和 form就可以使用
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var laydate = layui.laydate;

        laydate.render({
            elem: '#starttime' //指定元素
        });

        table.render({
            //表格的唯一ID
            id: "tableAll",
            //指定原始 table 容器的选择器或 DOM，方法渲染方式必填--此ID为body里的table组件
            elem: '#mytab',
            //标题
            title: '${uname.realname}的购物车车🥳🥳🥳',
            //开启分页共能
            page: true,
            //设定异步数据接口的额外参数，任意设
            where: {
                id: "",
                name: ""
            },
            limit: 5,
            url: "userShoppingCarServlet",
            cols: [
                [ //表头
                    {
                        type: 'checkbox',  //复选框
                        fixed: 'left',
                        align: 'center'
                    },
                    {
                        field: 'id',
                        title: '编号',
                        sort: true,
                        fixed: 'left',
                        align: 'center',
                        hide: 'true'
                    }, {
                    field: 'name',
                    title: '商品名',
                    sort: true,
                    align: 'center'
                }, {
                    field: 'description',
                    title: '描述',
                    align: 'left',
                    sort: true,
                    align: 'center'
                }, {
                    field: 'sellprice',
                    title: '销售价',
                    align: 'center',
                    sort: true,
                    align: 'center'
                }, {
                    field: 'productount',
                    title: '库存',
                    sort: true,
                    align: 'center'
                }, {
                    field: 'sellcount',
                    title: '购买数量',
                    sort: true,
                    align: 'left',
                    hide: 'true'
                }, {
                    field: 'sellprice',
                    title: '单价',
                    sort: true,
                    style: 'color:red',
                    align: 'center'
                }
                ]
            ],
            //显示分页时一页显示的行数
            limits: [5, 8, 12, 20, '50', '100'],
            toolbar: '#toolbarDemo' // 添加表头工具类
        });
        //  表头工具类的渲染事件
        table.on('toolbar(mytab)', function(obj) {
            //获取选中的数据行
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) { //  对应lay-event
                case 'getCheckData':
                    var data = checkStatus.data;
                    // layer.alert(JSON.stringify(data), {offset:'170px'});
                    alert(data.indexOf);
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：' + data.length + ' 个', {offset:'270px'});
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选' : '未全选', {offset:'270px'})
                    break;
                case 'LAYTABLE_TIPS':
                    layer.alert('Table for layui-v' + layui.v, {offset:'270px'});
                    break;
            };
        });
    });
    function initAmount() {
        var total = 0;
        var tdListUser = $("mytab>tbody>tr").find("td:eq(6)");
        alert(tdListUser);
        // 遍历每个商品
        $("tr").each(function (i) {
            // 获取单价、数量
            var price = $(this).prev().prev().text();
            var num = $(this).prev().children("input").val();
            // 该商品的总金额
            var sum = parseFloat(price) * num;
            $(this).text(sum);
            total += sum;
        });
        // 订单总金额
        $("#m").text(total);
    }
</script>
</html>
