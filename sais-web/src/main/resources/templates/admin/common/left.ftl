<nav class="navbar-default navbar-static-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav" id="side-menu">
            <li class="nav-header" style="padding: 25px 20px;">
                <div class="dropdown profile-element">
                    <span>
                        <img alt="image" class="img-circle" height="60px" src="/static/common/images/icon.ico" />
                    </span>
                    <a data-toggle="dropdown" class="dropdown-toggle" href="/admin/user_list/lists">
                            <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold"><#if Session["admin_account"]??>${Session["admin_account"]}</#if></strong>
                         </span>  <span class="text-muted text-xs block">后台管理员<b class="caret"></b></span> </span>
                    </a>
                    <ul class="dropdown-menu animated fadeInRight m-t-xs">
                        <li><a href="/admin/change_password/change_password">修改密码</a></li>
                        <li class="divider"></li>
                        <li><a href="/admin/login/logout">安全退出</a></li>
                    </ul>
                </div>
                <div class="logo-element">
                    WR
                </div>
            </li>
            <li class="active">
                <a href="#"><i class="fa fa-edit" style="width: 18px"></i> <span class="nav-label">管理</span> <span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li><a href="/admin/user_list/lists">用户管理</a></li>
                    <li><a href="/admin/slide_list/lists">幻灯片管理</a></li>
                    <li><a href="/admin/complain_list/lists">投诉管理</a></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>