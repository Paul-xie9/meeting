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
            会议预定 > 查看会议室
        </div>
        <div>
            <div style="color: red">${error!''}</div>
            <div style="color: blue">${msg!''}</div>
        </div>
        <table class="listtable">
            <caption>所有会议室:</caption>
            <tr class="listheader">
                <th>门牌编号</th>
                <th>会议室名称</th>
                <th>容纳人数</th>
                <th>当前状态</th>
                <th>操作</th>
            </tr>
            <#if mrs??>
                <#list mrs as mr>
                    <tr>
                        <td>${mr.roomNum}</td>
                        <td>${mr.roomName}</td>
                        <td>${mr.capacity}</td>
                        <td>${(mr.status==1)?string('已占用','启用')}</td>
                        <td>
                            <a class="clickbutton" href="/room_details?roomId=${mr.roomId}">查看详情</a>
                        </td>
                    </tr>
                </#list>
            </#if>
        </table>
    </div>
</div>
<#include 'commons/foot.ftl'>
</body>
</html>