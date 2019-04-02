<@override name="body">
    <div class="main">
    <div class="left">
    <#include "../common/send-blog.ftl">
    <h4 class="weibo_list_title">搜索结果:所有包含“
        <span style="color:red">${keyword}</span>
        ”的微博</h4>
    <#include "../common/blog-list.ftl">
    </div>
    <#include "../common/profile.ftl">
    </div>
</@override>
<@extends name="../common/common.ftl"/>