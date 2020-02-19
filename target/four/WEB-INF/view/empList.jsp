<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/30
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工列表页面</title>
    <script src="../../static/layui/layui.js"></script>
    <link href="../../static/layui/css/layui.css" rel="stylesheet"/>

    <style>
        .layui-table-cell .layui-form-checkbox[lay-skin="primary"] {
            top: 50%;
            transform: translateY(-50%);
        }
    </style>

</head>
<body>
<br>
<div class="layui-row">
    <div class="layui-col-sm10 layui-col-sm-offset2">

        <div class="layui-form-item clearfix">
            <div class="layui-input-inline">
                <input type="text" class="layui-input" name="filter" id="filter">
            </div>
            <div class="layui-input-inline">
                <button type="button" class="layui-btn" id="query">
                    <i class="layui-icon">&#xe654;</i>查询
                </button>
            </div>
            <div class="fr">
                <button type="button" class="layui-btn" id="add">
                    <i class="layui-icon">&#xe654;</i>新增
                </button>
                <button type="button" class="layui-btn" id="del">
                    <i class="layui-icon">&#xe654;</i>删除
                </button>
                <button type="button" class="layui-btn" id="edit">
                    <i class="layui-icon">&#xe654;</i>编辑
                </button>
            </div>
        </div>
    </div>
</div>
<div class="layui-row">
    <div class="layui-col-sm10 layui-col-sm-offset1">
        <table id="tabEmp" class="layui-table layui-hide"></table>
    </div>
</div>


<%--自定义js放在body最后引入--%>
<script src="../../static/js/emplist.js"></script>
</body>
</html>
