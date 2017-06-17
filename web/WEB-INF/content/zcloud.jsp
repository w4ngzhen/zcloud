<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: mee
  Date: 2017/6/6
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>zcloud 榛云盘</title>
    <style type="text/css">
        body {
            text-align: center;
        }
        div {
            margin: auto;
        }
        div.border {
            border: black solid;
        }
        div.width0 {
            width: 600px;
            height: auto;
        }
    </style>
    <script type="text/javascript">
        function checkFile() {
            var file = document.getElementsByName("upload")[0].value;
            if (file != '') {
                return true;
            }
            alert("没有选中文件");
            return false;
        }
    </script>
</head>
<body>
<div class="border width0">
    <p>欢迎您！<s:property value="#session.username"/></p>
    <a href="userquit">退出登录</a>
</div>
<div class="border width0">
    <form method="POST" enctype="multipart/form-data" action="uploadPro" onsubmit="return checkFile()">
        上传文件：<input type="file" name="upload"/>
        <input name="uploadButton" type="submit" value="上传"/>
    </form>
</div>
<div>
    <s:iterator value="fileMap">
        文件名:<s:property value="key"/>
        大小:<s:property value="value"/>
    </s:iterator>
</div>
</body>
</html>
