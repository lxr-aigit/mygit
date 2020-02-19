<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/4
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="../../static/js/jquery-3.1.1.min.js"></script>
</head>
<body>

    <form id="form1"  method="post" enctype="multipart/form-data">
        <input type="file" style="display: none;" id="file1" name="file1">
        <a href="javascript:$('#file1').click()" >上传</a>
        <a href="javascript:del()">删除</a>
    </form>


    <%--用来显示图片--%>
    <div id="d1">

    </div>
    <%--用来存储图片存储路径--%>
    <input type="hidden" id="imgPath">

    <script>

        $(function(){
            //当选择完成图片之后，input-file的value属性会发生变化，也就是触发onchange事件
            $("#file1").change(function(){
                //开始进行表单的异步提交动作
                //将表单中的数据封装为一个form数据体
                var formdata = new FormData($("#form1")[0]);//[0]将jquery对象转换为dom对象

                //ajax调用后台，上传文件，保证页面无刷新
                $.ajax({
                    url:"uploadFile",
                    data:formdata,
                    type:"post",
                    async:true,
                    cache:false,
                    contentType:false,
                    processData:false,
                    success:function(data){
                        //data就是图片的路径
                        alert(data);
                        //展示图片
                        $("#d1").html("<img src='../../files/"+data+"'/>");
                        //将图片存储路径赋值给input隐藏域
                        $("#imgPath").val("files/"+data);
                    },
                    error:function(data){
                        alert("上传失败");
                    }

                })

            })
        })

        function del(){
            //用于删除图片
            //1.删除服务器上图片文件 2.删除页面中的img标签
            $.ajax({
                url:"delImg",
                data:{imgPath:$("#imgPath").val()},
                type:"post",
                async:true,
                dataType:"text",
                success:function(data){
                    alert(data);
                    //移除img标签
                    $("img").remove();
                    $("#file1").val("");
                },
                error:function(data){

                    alert("删除失败");
                }


            })


        }

    </script>

</body>
</html>
