<%--
  Created by IntelliJ IDEA.
  User: 86156
  Date: 2022/12/29
  Time: 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <script type="text/javascript"src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>

</head>
<body>
<div><h1 align="center" style="color: red">拼夕夕首页</h1> </div>
<div><h3>欢迎您，${username}！</h3></div>
<br><br>
<div>
    <a href="buycar"><button class="btn btn-warning">购物车</button></a>
    <br><br>
    <a href="goods?start=1"><button class="btn btn-primary">商品列表</button></a>
    <br><br>
    <a href="exit"><button class="btn btn-danger">退出</button> </a>

</div>

</body>
</html>
