<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/30
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工新增页面</title>
    <script src="../../static/layui/layui.js"></script>
    <link href="../../static/layui/css/layui.css" rel="stylesheet"/>

</head>
<body>
    <form action="addEmp" class="layui-form" id="empform">
        <br>
        <div class="layui-form-item">
            <label class="layui-form-label">员工编号：</label>
            <div class="layui-input-inline">
                <input type="text" name="empno" placeholder="请输入员工编号" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">员工姓名：</label>
            <div class="layui-input-inline">
                <input type="text" name="ename" lay-verify="required" lay-reqtext="员工姓名必须填写" placeholder="请输入员工姓名"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">工作：</label>
            <div class="layui-input-inline">
                <input type="text" name="job" placeholder="请输入员工工作" class="layui-input">
            </div>
        </div>
        <div class="layerBtnCenter btn-box btn-box-1" align="center">
            <button type="button"  class="layui-btn btn-save" id="ensure"
                    onclick="submit_add()">保存
            </button>
            <button type="button"  class="layui-btn btn-close" id="cancel"
                    onclick="close_add()">取消
            </button>
        </div>

    </form>

    <script>
        layui.use(["table","layer","form"], function () {
            var layer = layui.layer
            $ = layui.jquery;
        })

        var submit_add = function(){
            //ajax方式提交数据
            $.ajax({
                url:"addEmp",
                data:$("#empform").serialize(),//获取全部的表单数据
                type:"post",
                async:true,
                success:function(){
                    parent.location.reload(); // 父页面刷新
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引

                    parent.layer.close(index);
                },
                error:function(){
                    layer.msg("新增失败");
                }
            })
        }














        //关闭弹出层
        var close_add = function(){
            parent.location.reload(); // 父页面刷新
            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
            parent.layer.close(index);
        }

    </script>
</body>
</html>
