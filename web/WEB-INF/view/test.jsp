<%--
  Created by IntelliJ IDEA.
  User: 刘帮君
  Date: 2022-07-23
  Time: 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>用户保存</h1>
<form name="user" action="/id/save" method="post">

    <label>
        用户名:
        <input type="text" name="userName">
    </label>
    <label>
        密码:
        <input type="password" name="userPass">
    </label>
    <label>
        确认密码:
        <input type="password" name="repetaPass">
    </label>
    <label>
        电子邮箱:
        <input type="text" name="userEmail">
    </label>

    <input type="submit" value="保存">
</form>

<h1>用户保存的数据:</h1>

<table>
    <tr>
        <th>用户名</th>
        <th>用户密码</th>
        <th>用户电子邮件</th>
    </tr>
    <tr>
        <td>${user.userName}</td>
        <td>${user.userPass}</td>
        <td>${user.userEmail}</td>
    </tr>
</table>

</body>
</html>
