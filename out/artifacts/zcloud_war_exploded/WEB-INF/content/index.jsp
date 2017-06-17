<%--
  Created by IntelliJ IDEA.
  User: mee
  Date: 2017/6/6
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>用户名登录</title>
    <style type="text/css">
        body {
            text-align: center;
        }
        table.login {
            margin: auto;
            text-align: center;
            width: auto;
            height: auto;
            border: black solid;
        }
        td.td0 {
            height: 50px;
            width: 60px;
            border: red solid;
        }
        td.td1 {
            height: 50px;
            width: 180px;
            border: red solid;
        }
    </style>
    <script type="text/javascript">
        function checkInfo() {
            var nm = document.getElementsByName("username")[0].value.trim();
            var ps = document.getElementsByName("password")[0].value.trim();
            if ("" == nm || "" == ps) {
                alert("请输入完整登录信息!");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<form method="post" action="login" onsubmit="return checkInfo()">
    <s:fielderror/>
    <form action="login" method="post">
        <table class="login">
            <caption><h3>用户登录</h3></caption>
            <tr>
                <td class="td0">用户名</td>
                <td class="td1"><input type="text" name="user.username"></td>
            </tr>
            <tr>
                <td class="td0">密码</td>
                <td class="td1"><input type="password" name="user.password"></td>
            </tr>
            <tr>
                <td><input type="submit" value="登录"></td>
                <td><input type="button" value="注册" onclick="window.location.href='register'"/></td>
            </tr>
        </table>
    </form>
</form>
</body>
</html>