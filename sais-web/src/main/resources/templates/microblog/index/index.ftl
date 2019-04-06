<@override name="body">
    <div class="main">
        <div class="left">
            <#include "../common/sendblog.ftl">
            <h4 class="weibo_list_title">全部发帖</h4>
                <#include "../common/bloglist.ftl">
                <#include "../common/page.ftl">
        </div>
        <#include "../common/profile.ftl">
    </div>
</@override>
<@extends name="../common/common.ftl"/>