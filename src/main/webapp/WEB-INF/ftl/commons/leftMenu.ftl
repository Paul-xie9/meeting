<div class="page-sidebar">
    <div class="sidebar-menugroup">
        <div class="sidebar-grouptitle">个人中心</div>
        <ul class="sidebar-menu">
            <li class="sidebar-menuitem"><a href="/admin/to_notifications">最新通知</a></li>
            <li class="sidebar-menuitem active"><a href="/admin/to_mybookings">我的预定</a></li>
        </ul>
    </div>
    <div class="sidebar-menugroup">
        <div class="sidebar-grouptitle">人员管理</div>
        <ul class="sidebar-menu">
            <li class="sidebar-menuitem"><a href="/toRegister">员工注册</a></li>
            <#if currentUser?? && (currentUser.role == 2)>
                <li class="sidebar-menuitem"><a href="/admin/to_departments">部门管理</a></li>
                <li class="sidebar-menuitem"><a href="/admin/to_approveaccount">注册审批</a></li>
                <li class="sidebar-menuitem"><a href="/admin/to_searchemployees?status=1">搜索员工</a></li>
            </#if>
        </ul>
    </div>
    <div class="sidebar-menugroup">
        <div class="sidebar-grouptitle">会议预定</div>
        <ul class="sidebar-menu">
            <#if currentUser?? && (currentUser.role == 2)>
                <li class="sidebar-menuitem"><a href="/admin/to_addmeetingroom">添加会议室</a></li>
            </#if>
            <#if currentUser?? &&(currentUser.role>0) >
                <li class="sidebar-menuitem"><a href="/bookmeeting">预定会议</a></li>
            </#if>
            <li class="sidebar-menuitem"><a href="/to_meetingrooms">查看会议室</a></li>
            <li class="sidebar-menuitem"><a href="/to_searchmeetings">搜索会议</a></li>
        </ul>
    </div>
</div>