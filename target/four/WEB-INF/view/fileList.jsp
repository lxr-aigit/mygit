<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/5
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>文件下载</title>

    <style>
        #d1,#d2{
            float:left;
        }

        #ul1 li{
            list-style: none;
        }
    </style>

</head>
<body>

    <div id="d1">
        <ul>
            <c:forEach items="${list}" var="fname">
                <li>${fname}</li>
            </c:forEach>
        </ul>
    </div>

    <div id="d2">
        <ul id="ul1">
            <c:forEach items="${list_uuid}" var="fname_uuid">
                <li>
                    <a href="downLoad?fname=${fname_uuid}">下载</a>
                </li>
            </c:forEach>
        </ul>
    </div>


</body>
</html>
