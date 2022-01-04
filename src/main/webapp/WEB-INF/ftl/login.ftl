<!DOCTYPE html>
<html>
<head>
    <title>Meeting会议管理系统</title>
    <link rel="stylesheet" href="/styles/common.css"/>
</head>
<body>
<#include 'commons/top.ftl'>
<div class="page-body">
    <#include 'commons/leftMenu.ftl'>
    <div class="page-content">
        <div class="content-nav">
            登录
        </div>
        <form action="/login" method="post">
            <div>
                <div style="color: red">${error!''}</div>
                <div style="color: blue">${msg!''}</div>
            </div>
            <fieldset>
                <legend>登录信息</legend>
                <table class="formtable" style="width:50%">
                    <tr>
                        <td>账号名:</td>
                        <td>
                            <input name="username" id="accountname" type="text"/>
                        </td>
                    </tr>
                    <tr>
                        <td>密码:</td>
                        <td>
                            <input name="password" id="new" type="password"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" class="command">
                            <input type="submit" value="登录" class="clickbutton"
                                   onclick="window.location.href='notifiactions.html';"/>
                            <input type="button" value="返回" class="clickbutton" onclick="window.history.back();"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </form>
    </div>
</div>
<#include 'commons/foot.ftl'>
</body>
</html>