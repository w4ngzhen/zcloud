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

        table.file_list_table {
            margin: auto;
        }
        table.set_border {
            border: black solid;
        }
    </style>
    <script type="text/javascript" src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript">
        function checkFile() {
            var file = document.getElementsByName("upload")[0].value;
            if (file != '') {
                return true;
            }
            alert("没有选中文件");
            return false;
        }
        function checkSelectedFile(context, func) {
            var name = context.getElementsByTagName("input")[0].name;
            if (document.getElementsByName(name)[0].value.trim() == '') {
                alert("请选择想要" + func + "的文件");
                return false;
            }
            return true;
        }
        $(document).ready(function () {
            //将选择到的文件名设置在每一个form里面，以供提交
            function setFileName(fileName) {
                var fileFuns = ["selected", "download", "delete"];
                for (var i = 0; i < fileFuns.length; i++)
                    document.getElementsByName(fileFuns[i] + "File")[0].value = fileName;
            }
            //点击文件列表，获取文件信息
            var tds = $("#file_list td");
            tds.click(function () {
                //获取行数
                var trSeq = $(this).parent().parent().find("tr").index($(this).parent()[0]);
                //获取该行第一列中的数据，这里就是文件名
                var fileName = document.getElementById("file_list")
                    .getElementsByTagName("tr")[trSeq]
                    .getElementsByTagName("td")[0].innerHTML;
                setFileName(fileName);
                //alert("我选择了文件:" + fileName);
            });
        })
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
    <table id="file_list" class="file_list_table set_border">
        <caption>文件列表</caption>
        <tr>
            <th>文件名称</th>
            <th>文件大小</th>
            <th></th>
            <th></th>
        </tr>
        <s:iterator value="fileMap" status="st">
            <tr <s:if test="#st.odd">style="background-color: #bbbbbb"</s:if>>
                <td>
                    <s:property value="key"/>
                </td>
                <td>
                    <s:property value="value"/>MB
                </td>
            </tr>
        </s:iterator>
    </table>
</div>
<div class="showDiv">
    选择的文件: <input name="selectedFile" readonly type="text" value="">
</div>
<div class="buttonDiv">
    <table class="file_list_table">
        <tr>
            <td>
                <form id="fileDownload" action="/download" method="POST"
                      onsubmit="return checkSelectedFile(this, '下载')">
                    <input hidden type="text" name="downloadFile" value=""/>
                    <input type="submit" value="下载"/>
                </form>
            </td>
            <td>
                <form id="fileDelete" action="/delete" method="POST"
                      onsubmit="return checkSelectedFile(this, '删除')">
                    <input hidden type="text" name="deleteFile" value=""/>
                    <input type="submit" value="删除"/>
                </form>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
