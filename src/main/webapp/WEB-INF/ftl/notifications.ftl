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
            个人中心 > <a href="/admin/to_notifications">最新通知</a>
        </div>
        <div>
            <div style="color: red">${error!''}</div>
            <div style="color: blue">${msg!''}</div>
        </div>
        <table class="listtable">
            <caption>
                未来7天我要参加的会议:
            </caption>
            <tr class="listheader">
                <th style="width:300px">会议名称</th>
                <th>会议室</th>
                <th>起始时间</th>
                <th>结束时间</th>
                <th style="width:100px">操作</th>
            </tr>
            <#if ms??>
                <#list ms as m>
                    <tr>
                        <td>${m.meetingName}</td>
                        <td>${m.roomName}</td>
                        <td>${m.startTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                        <td>${m.endTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                        <td>
                            <a class="clickbutton" href="/to_meetingdetails?meetingId=${m.meetingRoomId}">查看详情</a>
                        </td>
                    </tr>
                </#list>
            <#else>
                <tr>未来七天不需要参加会议</tr>
            </#if>
        </table>
        <table class="listtable">
            <caption>
                已取消的会议:
            </caption>
            <tr class="listheader">
                <th style="width:300px">会议名称</th>
                <th>会议室</th>
                <th>起始时间</th>
                <th>结束时间</th>
                <th>取消原因</th>
                <th style="width:100px">操作</th>
            </tr>
            <#if cms??>
                <#list cms as cm>
                    <tr>
                        <td>${cm.meetingName}</td>
                        <td>${cm.roomName}</td>
                        <td>${cm.startTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                        <td>${cm.endTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                        <td>${cm.canceledReason!"没理由"}</td>
                        <td>
                            <a class="clickbutton" href="/to_meetingdetails?meetingId=${cm.meetingId}">查看详情</a>
                        </td>
                    </tr>
                </#list>
            <#else>
                <tr>没有取消的会议</tr>
            </#if>
        </table>

    </div>
</div>
<#include 'commons/foot.ftl'>
</body>
</html>