<%--
  Created by IntelliJ IDEA.
  User: 86156
  Date: 2022/12/29
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>购物车</title>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-2">
            <h4>[用户名：${username}]</h4>
        </div>
        <div class="col-md-8">
            <h1>购物车</h1>
            <br>
            <table class="table table-hover">
                <tr>
                    <th>商品名称</th>
                    <th>单价</th>
                    <th>数量</th>
                    <th>总价</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${buycarList}" var="buycar">
                    <tr>
                        <td>${buycar.bname}</td>
                        <td>${buycar.bprice}</td>
                        <td>${buycar.bcount}</td>
                        <td>${buycar.total_price}</td>
                        <td>
                            <button class="btn btn-danger" onclick="del('${buycar.bname}')">删除</button>
                        </td>
                    </tr>
                </c:forEach>

            </table>

            <a href="goods?start=1"><button class="btn btn-primary">商品列表</button> </a>
            &nbsp;&nbsp;
            <a href="home.jsp"><button class="btn btn-default">返回主页</button></a>

        </div>
        <div class="col-md-2"></div>
    </div>
</div>

<br>


</body>
</html>
<script>

    function del(bname){

//alert(111)
        //创建对象  AJAX
        var xmlhttp;
        if (window.XMLHttpRequest)
        {// code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp=new XMLHttpRequest();
        }
        else
        {// code for IE6, IE5
            xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
        //请求
        xmlhttp.open("GET","buycarDelCon?buycarName="+bname,true);
        xmlhttp.send();
        //响应
        xmlhttp.onreadystatechange=function()
        {
            if (xmlhttp.readyState==4 && xmlhttp.status==200)
            {
                var v = xmlhttp.responseText;
                //alert("v = "+v)
                if (v == "1"){
                    //删除成功
                    //alert("删除成功！")
                    //刷新页面
                    location.replace(location)

                }else {
                    //删除失败
                    alert("删除失败！")
                }
            }
        }


    }
</script>
