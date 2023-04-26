<%--
  Created by IntelliJ IDEA.
  User: 86156
  Date: 2022/12/29
  Time: 9:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<form action="login">
    <div style="color: red">${message}</div>
  用户名：<input type="text" name="username">
  <br>
  密码：<input type="password" name="password">
    <br>
  <input type="submit" value="登录">
</form>

</body>
</html>
