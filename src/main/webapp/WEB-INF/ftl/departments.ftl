<!DOCTYPE html>
<html>
<head>
    <title>Meeting会议管理系统</title>
    <link rel="stylesheet" href="/styles/common.css"/>

    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.0/jquery.js"></script>

</head>
<body>
<#include 'commons/top.ftl'>
<div class="page-body">
    <#include 'commons/leftMenu.ftl'>
    <div class="page-content">
        <div class="content-nav">
            人员管理 > 部门管理
        </div>
        <div>
            <div style="color: red">${error!''}</div>
            <div style="color: blue">${msg!''}</div>
        </div>
        <form action="/admin/add_department">
            <fieldset>
                <legend>添加部门</legend>
                部门名称:
                <input type="text" name="departmentname" id="departmentname" maxlength="20"/>
                <input type="submit" class="clickbutton" value="添加"/>
            </fieldset>
        </form>
        <table class="listtable">
            <caption>所有部门:</caption>
            <tr class="listheader">
                <th>部门编号</th>
                <th>部门名称</th>
                <th>操作</th>
            </tr>
            <#if deps??>
                <#list deps as dep>
                    <tr>
                        <td>${dep.departmentId}</td>
                        <td id="depname${dep.departmentId}">${dep.departmentName}</td>
                        <td>
                            <a class="clickbutton" href="#" id="edit${dep.departmentId}"
                               onclick="editDep(${dep.departmentId})">编辑</a>
                            <a class="clickbutton" style="display: none" href="#" id="cancel${dep.departmentId}"
                               onclick="cancelDep(${dep.departmentId})">取消</a>
                            <a class="clickbutton"
                               href="/admin/deletedep?departmentid=${dep.departmentId}">删除</a>
                        </td>
                    </tr>
                </#list>
            </#if>
        </table>
    </div>
</div>
<#include 'commons/foot.ftl'>

<script>
    var depname;

    function cancelDep(depid) {
        var editBtn = $('#edit' + depid);
        var cancelBtn = $('#cancel' + depid);
        var ele = $('#depname' + depid);
        cancelBtn.css('display', 'none');
        editBtn.html('编辑');
        ele.html(depname);
    }

    function editDep(depid) {
        var editBtn = $('#edit' + depid);
        var cancelBtn = $('#cancel' + depid);
        var ele = $('#depname' + depid);
        depname = ele.html();
        if (cancelBtn.css('display') == 'none') {
            cancelBtn.css('display', 'inline');
            editBtn.html('确定');
            var depName = ele.text();
            ele.html('<input type="text" value="' + depName + '" />')
        } else {
            var children = ele.children('input');
            var val = children.val();
            $.post('/admin/update_dep', {id: depid, name: val}, function (msg) {
                if (msg == 'success') {
                    cancelBtn.css('display', 'none');
                    editBtn.html('编辑');
                    ele.html(val);
                }
            })
        }
    }
</script>

</body>
</html>