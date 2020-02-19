<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/4
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
    <script src="../../static/layui/layui.js"></script>
    <link rel="stylesheet" href="../../static/layui/css/layui.css"/>
</head>
<body>

<div class="layui-row">
    <div class="layui-col-sm-4 layui-col-sm-offset4">
        <fieldset class="layui-elem-field " style="margin-top: 30px;width: 400px;">
            <legend>用户登录</legend>
            <form class="layui-form" action="login" method="post">
                <br>
                <div class="layui-form-item">
                    <label class="layui-form-label">用户名：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="username" placeholder="请输入用户名" class="layui-input" size="20px">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">密码：</label>
                    <div class="layui-input-inline">
                        <input type="password" name="pwd" placeholder="请输入密码" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="formDemo">登&nbsp;&nbsp;&nbsp;录</button>
                    </div>
                </div>

            </form>
        </fieldset>
    </div>
</div>

<script>
    layui.use('form', function () {
        var form = layui.form
    });
</script>

</body>
</html>
