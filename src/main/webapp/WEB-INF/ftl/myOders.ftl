<!DOCTYPE html>
<html>
<head>
    <title>Meeting会议管理系统</title>
    <link rel="stylesheet" href="/styles/common.css"/>
    <style type="text/css">

    </style>
</head>
<body>
<#include 'commons/top.ftl'>
<div class="page-body">
    <#include 'commons/leftMenu.ftl'>
    <div class="page-content">
        <div class="content-nav">
            个人中心 > 我的预定
        </div>
        <div>
            <div style="color: red">${error!''}</div>
            <div style="color: blue">${msg!''}</div>
        </div>
        <table class="listtable">
            <caption>我预定的会议：</caption>
            <tr class="listheader">
                <th>会议名称</th>
                <th>会议室名称</th>
                <th>会议开始时间</th>
                <th>会议结束时间</th>
                <th>会议预定时间</th>
                <th>操作</th>
            </tr>
            <#if mlist??>
                <#list mlist as m >
                    <tr>
                        <td>${m.meetingName}</td>
                        <td>${m.roomName}</td>
                        <td>${m.startTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                        <td>${m.endTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                        <td>${m.reservationTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                        <td>
                            <a class="clickbutton" href="/mymeetingdetails?meetingid=${m.meetingId}">查看/撤销</a>
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