
/*加载table模块*/
layui.use(["table","layer"], function () {
    var table = layui.table
        ,layer = layui.layer
    $ = layui.jquery;


    /**
     * 加载表格数据
     */
    table.render({
        id:"tab"
        ,elem: "#tabEmp"  //tableid
        , height: 450   //表格高度
        , url: "/getAllEmp"   //数据接口
        ,request: {
            pageName: 'curr' //页码的参数名称，默认：page
            ,limitName: 'nums' //每页数据量的参数名，默认：limit
        }
        , page: true  //开启分页
        ,limits:[5,10,15,20]  //可选择的每页显示条数
        , cols: [[    //表头
            {type:'checkbox'} //开启复选框
            ,{field: 'empno', title: '员工编号', width: 150, sort: true}
            , {field: 'ename', title: '员工姓名', width: 150}
            , {field: 'job', title: '工作', width: 150}
        ]]
    })

    /**
     * 根据员工姓名进行模糊查询
     */
    $('#query').click(function(){
        //获取用户的查询条件
        var filter = $('#filter').val();

        table.reload("tab",{ //对应的table.render指定的id属性值
            where:{ename:filter}  //where对应的是过滤条件
        })
    })





    /**员工信息删除
     *
     */
    $("#del").click(function(){
        //获取表格的选中记录
        var checkStatus = table.checkStatus("tab");
        //判断用户是否选择记录
        if(checkStatus.data.length == 0){
            layer.msg("请选择要删除的记录");
            return;
        }

        //定义数组empnos
        var empnos = [];

        //定义要请求的后台url
        var delUrl = "delEmp?empno=";

        //遍历选中的记录，获取每条记录的员工编号

        layer.msg('确定要删除所选记录么？', {
            time: 0 //不自动关闭
            ,btn: ['确定', '取消']
            ,yes: function(index){
                $(checkStatus.data).each(function(i,emp){
                    //将员工编号存到数组中
                    empnos.push(emp.empno);

                    //将员工编号拼接到后台路径中
                    if(i == 0){//第一次循环
                        delUrl = delUrl + emp.empno;
                    }else{
                        delUrl = delUrl + "&empno=" + emp.empno;
                    }
                })

                // layer.msg(empnos.join(","));
                //layer.msg(delUrl);

                window.location.href = delUrl;
               // layer.close(index);//关闭弹出层
            }
        });


    })

    /**
     * 员工信息修改
      */
    $("#edit").click(function(){
        //获取用户的选中记录
        var checkStatus = table.checkStatus("tab");
        if(checkStatus.data.length == 0){
            layer.msg("请选择要编辑的记录");
            return;
        }
        if(checkStatus.data.length > 1){
            layer.msg("只能选择一条进行编辑操作");
            return;
        }
        //获取员工编号
        var empno = checkStatus.data[0].empno;

        //打开编辑弹出层
        layer.open({
            type:2,
            title:"员工编辑",
            area:["500px","450px"],
            content:"getEditEmp?empno="+empno  //将员工编号传递到后台用于获取员工信息
        })
    })

    /**
     * 新增员工
     */
    $("#add").click(function(){
        layer.open({
            type:2,
            title:"员工新增",
            area:["500px","450px"],
            content:"getAddEmp"
        })
    })

    /**
     * 注销操作
     */
    $("#logout").click(function(){
        window.location.href = "logout";
    })

})