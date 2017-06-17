<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: mee
  Date: 2017/6/8
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
    <!-- 页面的简单css -->
    <style type="text/css">
        body {
            text-align: center;
        }

        table.register {
            text-align: center;
            width: auto;
            height: auto;
            border: black solid;
            margin: auto;
            margin-top: 5px;
        }

        td.normal {
            height: auto;
            width: 80px;
            border: red solid;
        }

        td.info {
            height: auto;
            width: 20px;
            border: red solid;
        }

        td.button_td {
            height: 30px;
            width: 100px;
            border: red solid;
        }

        input.form_button1 {
            height: inherit;
            width: 80px;
        }

        input.form_button2 {
            height: inherit;
            width: 120px;
        }
    </style>
    <script type="text/javascript" src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript">


        //页面加载完成之后添加监听器
        $(document).ready(function () {
            //提示信息
            var tips = ["小于10个字符，不含中文", "6到15个数字、英文字符", "6到15个数字、英文字符"];
            //检查提示信息输出的td组件名称
            var outputInfoElesName = ["username_check", "password_check", "password_check1"];
            for (var i = 0; i < tips.length; i++) {
                document.getElementsByName(outputInfoElesName[i])[0].value = tips[i];
            }
            //提示提示框上一个信息的存储
            var pre_tips;
            //添加监听器
            document.getElementById("mouseoverTipArea0").addEventListener("mouseover", function () {
                var outputInfoEles = document.getElementsByName(outputInfoElesName[0])[0];
                pre_tips = outputInfoEles.value;
                outputInfoEles.value = tips[0];
            });
            document.getElementById("mouseoverTipArea0").addEventListener("mouseout", function () {
                document.getElementsByName(outputInfoElesName[0])[0].value = pre_tips;
            });
            document.getElementById("mouseoverTipArea1").addEventListener("mouseover", function () {
                var outputInfoEles = document.getElementsByName(outputInfoElesName[1])[0];
                pre_tips = outputInfoEles.value;
                outputInfoEles.value = tips[1];
            });
            document.getElementById("mouseoverTipArea1").addEventListener("mouseout", function () {
                document.getElementsByName(outputInfoElesName[1])[0].value = pre_tips;
            });
            document.getElementById("mouseoverTipArea2").addEventListener("mouseover", function () {
                var outputInfoEles = document.getElementsByName(outputInfoElesName[2])[0];
                pre_tips = outputInfoEles.value;
                outputInfoEles.value = tips[2];
            });
            document.getElementById("mouseoverTipArea2").addEventListener("mouseout", function () {
                document.getElementsByName(outputInfoElesName[2])[0].value = pre_tips;
            });
        });

        //检查用户名合法且唯一，经过AJAX进行。
        function checkUsernameAvailableByAJAX() {
            var registeringName = document.getElementsByName("user.username")[0].value;
            //先检查是否为空
            if (registeringName == "") {
                document.getElementsByName("username_check")[0].value = "请输入用户名再检查";
                return;
            }
            //alert(registeringName);
            //AJAX进行异步验证
            $.ajax({
                type: "POST",
                url: "usercheck",
                data: {"registeringName": registeringName},
                dataType: "text",
                success: function (data) {
                    document.getElementsByName("username_check")[0].value = data;
                    document.getElementsByName("flag")[0].innerText = "true";
                },
                error: function () {
                    alert("出错了");
                }
            });
        }
        //客户端合法输入验证
        function checkInfo() {
            //alert("我检查了网页上输入是否合法");
            var username = document.getElementsByName("user.username")[0].value;
            var passwdtemp = document.getElementsByName("passwdtemp")[0].value;
            var passwd = document.getElementsByName("user.password")[0].value;
            //alert("验证了是否为空");
            if (username == '' || passwdtemp == '' || passwd == '') {
                if (username == "") {
                    document.getElementsByName("username_check")[0].value = "请输入用户名";
                } else if (passwdtemp == "") {
                    document.getElementsByName("password_check")[0].value = "请输入密码";
                } else if (passwd == "") {
                    document.getElementsByName("password_check1")[0].value = "请再次输入密码";
                }
                return false;
            }
            //alert("确认了账户唯一");
            if (document.getElementsByName("flag")[0].innerText == "false") {
                document.getElementsByName("username_check")[0].value = "请验证账户是否合法";
                return false;
            }
            //alert("验证了两次密码一样");
            if (passwdtemp != passwd) {
                document.getElementsByName("password_check1")[0].value = "两次密码不一样";
                return false;
            }
            return false;
        }
    </script>
</head>
<body>
<!--该flag表明是否已经通过了AJAX验证用户名唯一了-->
<p hidden name="flag">false</p>

<form method="POST" action="registerok" onsubmit="return checkInfo()">
    <table class="register">
        <caption>注册</caption>
        <tr>
            <td class="normal">用户名</td>
            <td class="normal"><input type="text" name="user.username" onblur="checkUsernameAvailableByAJAX()"/></td>
            <td class="info" id="mouseoverTipArea0">?</td>
            <td class="normal"><input type="text" name="username_check" value=""/></td>
        </tr>
        <tr>
            <td class="normal">密码</td>
            <td class="normal"><input type="password" name="passwdtemp"/></td>
            <td class="info" id="mouseoverTipArea1">?</td>
            <td class="normal"><input type="text" name="password_check" value="" disabled/></td>
        </tr>
        <tr>
            <td class="normal">重复密码</td>
            <td class="normal"><input type="password" name="user.password"/></td>
            <td class="info" id="mouseoverTipArea2">?</td>
            <td class="normal"><input type="text" name="password_check1" value="" disabled/></td>
        </tr>
    </table>
    <table class="register">
        <tr>
            <td class="button_td">
                <input class="form_button1" type="submit" value="提交"/>
            </td>
            <td class="button_td">
                <input class="form_button1" type="reset" value="重新填写"/>
            </td>
            <td class="button_td">
                <input class="form_button2" type="button" value="已有账号，返回登录"
                       onclick="window.location.href='index'"/>
            </td>
        </tr>
    </table>
</form>
<s:fielderror/>
</body>
</html>
