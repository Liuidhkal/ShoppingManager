<%--
  Created by IntelliJ IDEA.
  User: 86156
  Date: 2022/12/29
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<html>
<head>
    <title>商品列表</title>
    <script type="text/javascript"src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>

</head>

<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <h1>商品列表</h1> <br>


            <table class="table table-bordered">
                <tr>
                    <th>id</th>
                    <th>名称</th>
                    <th>价格</th>
                    <th>库存</th>
                    <th>购买</th>
                </tr>

                <c:forEach items="${listGoods}" var="goods">
                    <tr>
                        <td>${goods.id}</td>
                        <td>${goods.gname}</td>
                        <td>${goods.price}</td>
                        <td>${goods.count}</td>

                        <td>
                                <%--<input class="a" type="text"  id="goodsId"  name="goodsId" value="${goods.id}" >--%>
                            数量：<input type="text" id="goodsBuyCount${goods.id}" name="goodsCount" value="1">
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                            <button class="btn btn-success" onclick="f(${goods.id}, ${goods.count})" >加入购物车</button>
                        </td>
                    </tr>
                </c:forEach>

            </table>
            <div align="center">
                <a href="goods?start=1"><button class="btn btn-default">首页</button></a>
                &nbsp;&nbsp;
                <a href="goods?start=${sessionScope.up}"><button class="btn btn-default">上一页</button></a>
                &nbsp;
                <a href="goods?start=${sessionScope.next}"><button class="btn btn-default">下一页</button></a>
                &nbsp;&nbsp;
                <a href="goods?start=${sessionScope.end}"><button class="btn btn-default">尾页</button></a>

            </div>

            <a href="home.jsp" class="btn btn-default">返回主页</a>
            &nbsp;&nbsp;
            <a href="buycar" class="btn btn-warning">查看购物车</a>




        </div>
        <div class="col-md-2"></div>
    </div>
</div>




</body>
</html>
<script>
    /*当bt1发生点击事件时，获取text文本框内容和商品id*/
    function f(id, kuCun) {

        var text = document.getElementById("goodsBuyCount" + id).value;

        if (text > kuCun){
            //库存不足
            alert("库存不足！")
        }else {

            //alert(111)
            //var text = $("#goodsCount").val();
            //var goodsId = $("#goodsId").val();
            // alert(goodsId)
            //AJAX
            var xmlhttp;
            if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
                xmlhttp = new XMLHttpRequest();
            } else {// code for IE6, IE5
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            }

            //请求
            xmlhttp.open("GET", "shopping?text=" + text + "&goodsId=" + id, true);
            xmlhttp.send();

            //alert("请求成功")
            //响应
            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                    var v = xmlhttp.responseText;
                    //alert(v)
                    if (v == "-1") {
                        //添加失败
                        alert("添加失败！")
                    }else {
                        //添加成功
                        //刷新页面
                        alert("添加成功！")

                        location.replace(location)
                        //$("tdCount").html(v)
                    }
                }
            }

            //if else
        }


    }
</script>
