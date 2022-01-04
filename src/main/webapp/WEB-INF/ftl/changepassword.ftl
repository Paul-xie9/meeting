<!DOCTYPE html>
<html>
<head>
    <title>Meeting会议管理系统</title>
    <link href="/styles/common.css" rel="stylesheet"/>
</head>
<body>
<#include 'commons/top.ftl'>
<div class="page-body">
    <#include 'commons/leftMenu.ftl'>
    <div class="page-content">
        <div class="content-nav">
            修改密码
        </div>
        <div>
            <div style="color: red">${error!''}</div>
            <div style="color: blue">${msg!''}</div>
        </div>
        <form action="/admin/change_password" method="post">
            <a hidden name="employeeid">${employee.getEmployeeId()}</a>
            <fieldset>
                <legend>修改密码信息</legend>
                <table class="formtable" style="width:50%">
<#--                    <tr>-->
<#--                        <td>账号名:</td>-->
<#--                        <td>-->
<#--                            <input id="username" name="username" type="password"/>-->
<#--                        </td>-->
<#--                    </tr>-->
                    <tr>
                        <td>原密码:</td>
                        <td>
                            <input id="old" name="password" type="password"/>
                        </td>
                    </tr>
                    <tr>
                        <td>新密码:</td>
                        <td>
                            <input id="new1" name="newpassword1" type="password"/>
                        </td>
                    </tr>
                    <tr>
                        <td>再次输入:</td>
                        <td>
                            <input id="new2" name="newpassword2" type="password" onkeyup="checkpassword()"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" class="command">
                            <input type="submit" value="确认修改" class="clickbutton"/>
                            <input type="button" value="返回" class="clickbutton" onclick="window.history.back();"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </form>
    </div>
</div>
<script type="text/javascript">
    function checkpassword() {
        var password = document.getElementById("new1").value;
        var repassword = document.getElementById("new2").value;

        if(password == repassword) {
            document.getElementById("tishi").innerHTML="<br><font color='green'>两次密码输入一致</font>";
            document.getElementById("submit").disabled = false;

        }else {
            document.getElementById("tishi").innerHTML="<br><font color='red'>两次输入密码不一致!</font>";
            document.getElementById("submit").disabled = true;
        }
    }
</script>
<#include 'commons/foot.ftl'>
</body>
</html>